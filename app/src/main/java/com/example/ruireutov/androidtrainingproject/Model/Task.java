package com.example.ruireutov.androidtrainingproject.Model;

public class Task {
    private final int id;
    private String label;
    private boolean status;

    public Task(int id, String label) {
        this.id = id;
        this.label = label;
        status = false;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public boolean isDone () { return status; }
    public void setStatus(boolean isDone) { status = isDone; }
}
