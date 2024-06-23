package com.example.family_task_tracker;

public class Tasks {

    private String name; // название
    private String text;  // текст
    private int balls; // ресурс флага

    public Tasks(String name, String text, int balls){

        this.name=name;
        this.text=text;
        this.balls=balls;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBalls() {
        return this.balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }
}