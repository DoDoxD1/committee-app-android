package com.example.committee;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MemberDetailsActivity extends AppCompatActivity {

    TextView nameTextView, IdTextView;
    Button isPaidButton, removeMemberButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Boolean isPaid;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_member_details);

        // find the views
        nameTextView = findViewById(R.id.name);
        IdTextView = findViewById(R.id.id);
        isPaidButton = findViewById(R.id.button_paid);
        removeMemberButton = findViewById(R.id.button_remove);

        // populate the views
        Intent intent = getIntent();
        String Id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        isPaid = Objects.requireNonNull(intent.getExtras()).getBoolean("isPaid");
        IdTextView.setText(Id);
        nameTextView.setText(name);
        if (isPaid) {
            isPaidButton.setText("Paid");
        } else {
            isPaidButton.setText("Not Paid");
        }

        isPaidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPaid) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MemberDetailsActivity.this);
                    builder.setMessage("Do you want to mark this member as paid?")
                            .setTitle("Mark as Paid");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User taps OK button.
                            isPaid = true;
                            syncDB(Id, isPaid);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancels the dialog.
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void syncDB(String id, boolean isPaid) {
        db.collection("members").document(id).update("isPaid", true).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Void unused) {
                        isPaidButton.setText("Paid");
                        Toast.makeText(MemberDetailsActivity.this, "Paid Status Updated", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MemberDetailsActivity.this, "Error Updating Paid Status", Toast.LENGTH_LONG).show();
                    }
                });
    }
}