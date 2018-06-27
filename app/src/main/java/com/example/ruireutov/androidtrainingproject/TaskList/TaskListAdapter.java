package com.example.ruireutov.androidtrainingproject.TaskList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruireutov.androidtrainingproject.Model.Task;
import com.example.ruireutov.androidtrainingproject.R;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends BaseAdapter {

    private final TaskAdapterCallback taskAdapterCallback;
    private final List<Task> taskList;

    public interface TaskAdapterCallback{
        void onTaskChanged(Task task);
        void onTaskUpdate(Task task);
        void onTaskDelete(Task task);
    }


    TaskListAdapter(TaskAdapterCallback callback) {
        this.taskList = new ArrayList<>();
        taskAdapterCallback = callback;
    }

    static class ViewHolder {

        private final TextView label;
        private final CheckBox checkBox;
        private final ImageView editButton;
        private final ImageView deleteButton;
        private final TaskAdapterCallback callback;
        private Task task;

        ViewHolder(View itemView, final TaskAdapterCallback callback1) {
            this.label = itemView.findViewById(R.id.text_task_name);
            this.checkBox = itemView.findViewById(R.id.checkbox_task_status);
            this.editButton = itemView.findViewById(R.id.button_edit_task);
            this.deleteButton = itemView.findViewById(R.id.button_delete_task);

            this.callback = callback1;

            this.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setStatus(isChecked);
                    callback.onTaskChanged(task);
                }
            });

            this.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onTaskUpdate(task);
                }
            });

            this.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onTaskDelete(task);
                }
            });
        }

        void bind(Task data){
            task = data;
            label.setText(data.getLabel());
            checkBox.setChecked(data.isDone());
        }
    }

    public void setTaskList(List<Task> taskList) {
        if(taskList != null) {
            this.taskList.clear();
            this.taskList.addAll(taskList);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return taskList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_task, parent, false);

            viewHolder = new ViewHolder(convertView, taskAdapterCallback);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.bind(taskList.get(position));

        return convertView;
    }

    public Task addTask(String description) {
        int id = 0;
        if (taskList.size() > 0) {
            id = taskList.get(taskList.size() - 1).getId() + 1;
        }
        Task task = new Task(id, description);
        taskList.add(task);
        notifyDataSetChanged();
        return task;
    }

    public Task updateTask(int id, String label) {
        for(Task t : taskList) {
            if(id == t.getId()) {
                t.setLabel(label);
                notifyDataSetChanged();
                return t;
            }
        }

        return null;
    }

    public Task deleteTask(int id) {
        for(Task t : taskList) {
            if(id == t.getId()) {
                taskList.remove(t);
                notifyDataSetChanged();
                return t;
            }
        }

        return null;
    }
}
