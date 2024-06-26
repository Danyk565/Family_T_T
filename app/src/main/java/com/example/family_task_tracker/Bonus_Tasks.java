package com.example.family_task_tracker;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class Bonus_Tasks extends ArrayList<Parcelable> implements Parcelable {
    public String Name_task;
    public String Task_conditions;
    public boolean Task_control;
    public String Uid;
    public String scores;
    public String Type;

    public Bonus_Tasks(String Name_task, String Task_conditions, boolean Task_control, String Uid, String scores,String Type){
        this.Name_task = Name_task;
        this.Task_conditions = Task_conditions;
        this.Task_control = Task_control;
        this.Uid = Uid;
        this.scores = scores;
        this.Type = Type;
    }

    public Bonus_Tasks(){};

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Bonus_Tasks(Parcel in) {
        Name_task = in.readString();
        Task_conditions = in.readString();
        Task_control = in.readBoolean();
        Uid = in.readString();
        scores = in.readString();
        Type = in.readString();
    }

    public static final Creator<Bonus_Tasks> CREATOR = new Creator<Bonus_Tasks>() {
        @Override
        public Bonus_Tasks createFromParcel(Parcel in) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return new Bonus_Tasks(in);
            }
            return null;
        }

        @Override
        public Bonus_Tasks[] newArray(int size) {
            return new Bonus_Tasks[size];
        }
    };

    public String getUid() {
        return Uid;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getName_task() {
        return Name_task;
    }

    public void setName_task(String name_task) {
        this.Name_task = name_task;
    }

    public boolean isTask_control() {
        return Task_control;
    }

    public void setTask_control(boolean task_control) {
        this.Task_control = task_control;
    }

    public String getTask_conditions() {
        return Task_conditions;
    }

    public void setTask_conditions(String task_conditions) {
        this.Task_conditions = task_conditions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Name_task);
        dest.writeString(Task_conditions);
        dest.writeBoolean(Task_control);
        dest.writeString(Uid);
        dest.writeString(scores);
        dest.writeString(Type);
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
