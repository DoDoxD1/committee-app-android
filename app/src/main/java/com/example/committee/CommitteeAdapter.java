package com.example.committee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommitteeAdapter extends RecyclerView.Adapter<CommitteeViewHolder> {

    Context context;
    List<Committee> committeeList;
    CommitteeSelectListener listener;

    public CommitteeAdapter(Context context, List<Committee> committeeList, CommitteeSelectListener listener) {
        this.context = context;
        this.committeeList = committeeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CommitteeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommitteeViewHolder(LayoutInflater.from(context).inflate(R.layout.committee_view,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommitteeViewHolder holder, int position) {
        Committee committee = committeeList.get(position);
        holder.title.setText(committee.getName());
        holder.numberOfMembers.setText(committee.getNumberOfMembers());
        holder.imageView.setImageResource(R.drawable.a);

        holder.card.setOnClickListener(view -> listener.onItemClicked(committee));
    }

    @Override
    public int getItemCount() {
        return committeeList.size();
    }
}
