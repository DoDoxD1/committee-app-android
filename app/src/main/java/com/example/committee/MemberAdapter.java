package com.example.committee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder> {

    Context context;
    List<MemberModel> memberList;
    MemberSelectedListener listener;

    public MemberAdapter(Context context, List<MemberModel> memberList, MemberSelectedListener listener) {
        this.context = context;
        this.memberList = memberList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(context).inflate(R.layout.member_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        MemberModel member = memberList.get(position);
        holder.title.setText(member.getName());
        holder.isPaidTextView.setText(member.getPaid()?"Paid":"Not Paid");
        holder.imageView.setImageResource(R.drawable.a);

        holder.card.setOnClickListener(view -> listener.onItemClicked(member));
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
