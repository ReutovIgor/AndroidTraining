package com.example.ruireutov.androidtrainingproject.TaskList;

public class TaskListPresenter implements ITaskListPresenterControl{
    private ITaskListViewControl viewControl;
    TaskListPresenter(ITaskListViewControl viewControl) {
        this.viewControl = viewControl;
    }
}
