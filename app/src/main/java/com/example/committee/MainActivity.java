package com.example.committee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements CommitteeSelectListener {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static String TAG = "aunu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_circular);
        getCommitteeData();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getCommitteeData() {
        progressBar.setVisibility(View.VISIBLE);
        List<Committee> committeeList = new ArrayList<>();
        db.collection("committees")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Committee committee = new Committee(Objects.requireNonNull(document.get("name")).toString(), Objects.requireNonNull(document.get("numberOfMembers")).toString());
                            committee.setId(document.getId());
                            committeeList.add(committee);
                        }
                        populateRecyclerView(committeeList);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    private void populateRecyclerView(List<Committee> committeeList) {

        progressBar.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommitteeAdapter(getApplicationContext(), committeeList, this));

    }

    @Override
    public void onItemClicked(Committee committee) {
        Intent intent = new Intent(MainActivity.this, MembersActivity.class);
        intent.putExtra("name", committee.getName());
        intent.putExtra("id", committee.getId());
        intent.putExtra("members", committee.getNumberOfMembers());
        MainActivity.this.startActivity(intent);
    }
}