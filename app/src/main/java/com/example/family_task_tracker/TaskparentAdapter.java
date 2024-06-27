package com.example.family_task_tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskparentAdapter extends RecyclerView.Adapter<TaskparentAdapter.ViewHolder> {
    private final List<Bonus_Tasks> dataList;
    private final List<Integer> drawableList;  // List of drawable resources
    private final LayoutInflater inflater;
    private final OnTaskClickListener listener;
    private final Context context;

    public interface OnTaskClickListener {
        void onTaskClick(Bonus_Tasks task);
    }

    public TaskparentAdapter(List<Bonus_Tasks> dataList, List<Integer> drawableList, Context context, OnTaskClickListener listener) {
        this.dataList = dataList;
        this.drawableList = drawableList;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_lay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bonus_Tasks data = dataList.get(position);
        holder.textView1.setText(data.getName_task());
        holder.textView2.setText(data.getScores());
        holder.textView3.setText(data.getType());

        // Set drawable image
        if (data.getType().equals("bonus_tasks")) {
            holder.imageView.setImageResource(drawableList.get(0));
        } else if (data.getType().equals("daily_tasks")) {
            holder.imageView.setImageResource(drawableList.get(1));
        }

        holder.itemView.setOnClickListener(v -> listener.onTaskClick(data));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;
        final TextView textView2;
        final TextView textView3;
        final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.NAme);
            textView2 = itemView.findViewById(R.id.Conditions);
            textView3 = itemView.findViewById(R.id.Type_tv);
            imageView = itemView.findViewById(R.id.imageView12);
        }
    }
}
