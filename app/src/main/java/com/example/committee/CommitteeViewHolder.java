package com.example.committee;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CommitteeViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title, numberOfMembers;
    CardView card;

    public CommitteeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
        title = itemView.findViewById(R.id.name);
        card = itemView.findViewById(R.id.card);
        numberOfMembers = itemView.findViewById(R.id.number_of_members);
    }
}
