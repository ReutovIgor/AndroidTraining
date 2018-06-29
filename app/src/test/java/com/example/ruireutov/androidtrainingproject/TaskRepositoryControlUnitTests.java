package com.example.ruireutov.androidtrainingproject;

import com.example.ruireutov.androidtrainingproject.DataStorage.IDataStorage;
import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.Repository.TaskRepositoryControl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TaskRepositoryControlUnitTests {
    @Test
    public void taskRepository_receives_stored_taskList() {
        MockFileDataStorage mockFileDataStorage = new MockFileDataStorage();
        mockFileDataStorage.addTask(new Task(0, "TaskName"));
        TaskRepositoryControl taskRepositoryControl = new TaskRepositoryControl(mockFileDataStorage);
        List<Task> tasks = taskRepositoryControl.getTasks();
        assertEquals(mockFileDataStorage.taskList, tasks);
    }

    @Test
    public void taskRepository_update_existing_task() {
        MockFileDataStorage mockFileDataStorage = new MockFileDataStorage();
        mockFileDataStorage.addTask(new Task(0, "TaskName"));
        TaskRepositoryControl taskRepositoryControl = new TaskRepositoryControl(mockFileDataStorage);
        List<Task> tasks = taskRepositoryControl.getTasks();
        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        task.setLabel("FirstTask_updated");
        taskRepositoryControl.updateTask(task);
        tasks = taskRepositoryControl.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void taskRepository_add_new_tasks() {
        MockFileDataStorage mockFileDataStorage = new MockFileDataStorage();
        TaskRepositoryControl taskRepositoryControl = new TaskRepositoryControl(mockFileDataStorage);
        taskRepositoryControl.addTask("TaskName");
        List<Task> tasks = taskRepositoryControl.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(mockFileDataStorage.taskList, tasks);

        taskRepositoryControl.addTask("TaskName2");
        tasks = taskRepositoryControl.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(mockFileDataStorage.taskList, tasks);
    }

    @Test
    public void taskRepository_delete_existing_tasks() {
        MockFileDataStorage mockFileDataStorage = new MockFileDataStorage();
        mockFileDataStorage.addTask(new Task(0, "TaskName"));
        TaskRepositoryControl taskRepositoryControl = new TaskRepositoryControl(mockFileDataStorage);
        List<Task> tasks = taskRepositoryControl.getTasks();
        assertEquals(1, tasks.size());

        Task task = tasks.get(0);
        taskRepositoryControl.deleteTask(task);
        tasks = taskRepositoryControl.getTasks();
        assertEquals(0, tasks.size());
    }

    class MockFileDataStorage implements IDataStorage{
        final List<Task> taskList;
        MockFileDataStorage() {
            taskList = new ArrayList<>();
        }

        void addTask(Task task) {
            taskList.add(task);
        }

        @Override
        public List<Task> getTaskList() {
            return taskList;
        }

        @Override
        public void saveTaskList(List<Task> taskList) {
            this.taskList.clear();
            this.taskList.addAll(taskList);
        }
    }
}