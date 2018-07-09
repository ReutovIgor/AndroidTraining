package com.example.ruireutov.androidtrainingproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ruireutov.androidtrainingproject.Views.TaskListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragments = manager.getFragments();
        if(fragments.size() == 0 ) {
            FragmentTransaction tr = manager.beginTransaction();
            tr.add(R.id.layout_fragment, new TaskListView(), "TaskListView");
            tr.commit();
        }
    }
}
