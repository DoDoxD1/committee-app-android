package com.example.committee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberDetailsActivity extends AppCompatActivity {

    TextView nameTextView, IdTextView;
    Button isPaidButton, removeMemberButton;

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
        Boolean isPaid = intent.getBooleanExtra("isPaid", false);

        IdTextView.setText(Id);
        nameTextView.setText(name);
        if (isPaid.equals("true")) {
            isPaidButton.setText("Paid");
        } else {
            isPaidButton.setText("Not Paid");
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}