package com.example.family_task_tracker;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    public static String email;
    public static String username;
    public static String role;
    public static String password;

    public User(String email, String username, String role, String password) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
    };
    protected User(Parcel in) {
        email = in.readString();
        username = in.readString();
        role = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public User() {};

    public static String getEmail() {
        return email;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(role);
        dest.writeString(password);
    }
}
