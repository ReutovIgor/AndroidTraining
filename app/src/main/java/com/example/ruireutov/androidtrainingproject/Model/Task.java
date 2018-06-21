package com.example.ruireutov.androidtrainingproject.Model;

public class Task {
    private final int id;
    private String label;
    private boolean status;

    public Task(int id, String description) {
        this.id = id;
        label = description;
        status = false;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }
    public boolean isDone () { return status; }
    public void setStatus(boolean isDone) { status = isDone; }
}
