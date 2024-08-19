package com.example.committee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembersActivity extends AppCompatActivity implements MemberSelectedListener{

    TextView nameTextView, membersTextView;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static String TAG = "aunu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_members);

        // get the views
        nameTextView = findViewById(R.id.title);
        membersTextView = findViewById(R.id.members);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);


        // populate the views
        Intent intent = getIntent();
        String Id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String numberOfMembers = intent.getStringExtra("members");
        nameTextView.setText(name);
        membersTextView.setText(numberOfMembers);

        // get the members data from firebase
        getMembersData(Id);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getMembersData(String Id) {
        progressBar.setVisibility(View.VISIBLE);
        List<MemberModel> membersList = new ArrayList<>();
        db.collection("members")
                .whereEqualTo("committee", Id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            MemberModel member = new MemberModel(Objects.requireNonNull(document.get("name")).toString(), (Boolean) Objects.requireNonNull(document.get("isPaid")));
                            member.setId(document.getId());
                            membersList.add(member);
                        }
                        populateRecyclerView(membersList);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private void populateRecyclerView(List<MemberModel> membersList) {

        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MemberAdapter(getApplicationContext(),membersList,this));

    }

    @Override
    public void onItemClicked(MemberModel member) {
        Intent intent = new Intent(MembersActivity.this, MemberDetailsActivity.class);
        intent.putExtra("name", member.getName());
        intent.putExtra("id", member.getId());
        intent.putExtra("isPaid", member.getPaid());
        MembersActivity.this.startActivity(intent);
    }
}