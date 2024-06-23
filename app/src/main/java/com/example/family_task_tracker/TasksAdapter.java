package com.example.family_task_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TasksAdapter  extends RecyclerView.Adapter<TasksAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Tasks> task;

    TasksAdapter(Context context, List<Tasks> task) {
        this.task = task;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksAdapter.ViewHolder holder, int position) {
        Tasks tasks = task.get(position);
        holder.textV.setText(tasks.getText());
        holder.nameV.setText(tasks.getName());
        holder.ballsV.setText(tasks.getBalls());
    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameV, textV, ballsV;
        ViewHolder(View view){
            super(view);
            ballsV = view.findViewById(R.id.balls_rv);
            nameV = view.findViewById(R.id.name_rv);
            textV = view.findViewById(R.id.text_rv);
        }
    }
}