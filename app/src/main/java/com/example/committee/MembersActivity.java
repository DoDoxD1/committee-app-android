package com.example.committee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MembersActivity extends AppCompatActivity {

    TextView nameTextView, membersTextView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_members);

        nameTextView = findViewById(R.id.title);
        membersTextView = findViewById(R.id.members);
        recyclerView = findViewById(R.id.recycler_view);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String numberOfMembers = intent.getStringExtra("members");

        nameTextView.setText(name);
        membersTextView.setText(numberOfMembers);

        List<MemberModel> memberList = new ArrayList<MemberModel>();
        memberList.add(new MemberModel("Name",true));
        memberList.add(new MemberModel("Arihant",false));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MemberAdapter(getApplicationContext(),memberList));







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}