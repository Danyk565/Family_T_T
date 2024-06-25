package com.example.family_task_tracker;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private List<Bonus_Tasks> dataList;
    private final LayoutInflater inflater;

    public TasksAdapter(List<Bonus_Tasks> dataList, Context context) {
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;
        final TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.NAme);
            textView2 = itemView.findViewById(R.id.Conditions);
        }
    }
}

/*package com.example.family_task_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private List<Bonus_Tasks> dataList;
    private final LayoutInflater inflater;

    public TasksAdapter( List<Bonus_Tasks> dataList,Context context) {
        this.dataList=dataList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_lay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Bonus_Tasks data = dataList.get(position);
        holder.textView1.setText(data.Name_task);
        holder.textView2.setText(data.Task_conditions);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView1;
        final TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.NAme);
            textView2 = itemView.findViewById(R.id.Conditions);
        }
    }
}*/
/*import android.content.Context;
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
}*/