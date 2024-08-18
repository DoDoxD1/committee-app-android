package com.example.committee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommitteeSelectListener{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List<Committee> committeeList = new ArrayList<Committee>();

        committeeList.add(new Committee("Name of Comm",10));
        committeeList.add(new Committee("Name of Comm2",20));
        committeeList.add(new Committee("Name of Comm",25));


        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommitteeAdapter(getApplicationContext(),committeeList, this));




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClicked(Committee committee) {
        Intent intent = new Intent(MainActivity.this,MembersActivity.class);
        intent.putExtra("name",committee.getName());
        intent.putExtra("members",Integer.toString(committee.getNumberOfMembers()));
        MainActivity.this.startActivity(intent);
        Toast.makeText(this, committee.getName(), Toast.LENGTH_LONG).show();
    }
}