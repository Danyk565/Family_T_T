package com.example.family_task_tracker;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Bonus_Tasks implements Parcelable  {
    public static String Name_task;
    public static String Task_conditions;
    public static boolean Task_control;
    public static String Uid;
    public static String  scores;

    public Bonus_Tasks(String Name_task, String Task_conditions, boolean Task_control, String Uid, String scores){
        this.Name_task = Name_task;
        this.Task_conditions = Task_conditions;
        this.Task_control = Task_control;
        this.Uid = Uid;
        this.scores = scores;
    }

    public Bonus_Tasks(){};
    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Bonus_Tasks(Parcel in) {
        Name_task = in.readString();
        Task_conditions = in.readString();
        Task_control = in.readBoolean();
        Uid = in.readString();
        scores = in.readString();
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
    public static String getUid() {
        return Uid;
    }

    public static String getScores() {
        return scores;
    }

    public static void setScores(String scores) {
        Bonus_Tasks.scores = scores;
    }

    public static void setUid(String uid) {
        Uid = uid;
    }

    public static String getName_task() {
        return Name_task;
    }

    public static void setName_task(String name_task) {
        Name_task = name_task;
    }

    public static boolean isTask_control() {
        return Task_control;
    }

    public static void setTask_control(boolean task_control) {
        Task_control = task_control;
    }

    public static String getTask_conditions() {
        return Task_conditions;
    }

    public static void setTask_conditions(String task_conditions) {
        Task_conditions = task_conditions;
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
    }
}
