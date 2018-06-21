package com.example.ruireutov.androidtrainingproject;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ruireutov.androidtrainingproject.TaskList.TaskListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction tr = getFragmentManager().beginTransaction();
        tr.add(R.id.layout_fragment, new TaskListView(), "TaskListView");
        tr.commit();
    }
}
