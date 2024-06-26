package com.example.family_task_tracker;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private final List<Bonus_Tasks> dataList;
    private final LayoutInflater inflater;
    private final OnTaskClickListener listener;
    private final Context context;

    public interface OnTaskClickListener {
        void onTaskClick(Bonus_Tasks task);
    }

    public TasksAdapter(List<Bonus_Tasks> dataList, Context context, OnTaskClickListener listener) {
        this.dataList = dataList;
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
        holder.textView2.setText(data.getTask_conditions());
        holder.textView3.setText(data.getType());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Text_task.class);
            intent.putExtra("taskName", data.getName_task());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;
        final TextView textView2;
        final TextView textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.NAme);
            textView2 = itemView.findViewById(R.id.Conditions);
            textView3 = itemView.findViewById(R.id.Type_tv);
        }
    }
}

