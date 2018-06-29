package com.example.ruireutov.androidtrainingproject;

import android.os.Parcel;
//import android.support.test.runner.AndroidJUnit4;

import com.example.ruireutov.androidtrainingproject.Model.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
//@RunWith(AndroidJUnit4.class)
public class TaskUnitTests {
    @Test
    public void task_getters_setters() {
        Task task = new Task(0, "TaskName");
        assertEquals("TaskName", task.getLabel());
        assertEquals(0, task.getId());
        assertEquals(false, task.isDone());
        task.setLabel("TaskName2");
        task.setStatus(true);
        assertEquals("TaskName2", task.getLabel());
        assertEquals(true, task.isDone());
    }

    @Test
    public void task_parcelable() {
        Task task = new Task(0, "TaskName");
        Parcel parcel = Parcel.obtain();
        task.writeToParcel(parcel, task.describeContents());

        Task pTask = Task.CREATOR.createFromParcel(parcel);
        assertEquals("TaskName", pTask.getLabel());
        assertEquals(0, pTask.getId());
        assertEquals(false, pTask.isDone());
    }
}
