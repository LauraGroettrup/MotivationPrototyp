package com.joanneum.motivation.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joanneum.motivation.R;


public class TaskAdapter extends BaseAdapter {
    final Context context;

    private final String[] tasks;

    TaskAdapter(Context context, String[] tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.length;
    }

    @Override
    public Object getItem(int position) {
        return tasks[position];
    }

    @Override
    public long getItemId(int position) {
        return ((long) position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Format: name-EP
        String task = tasks[position];
        String[] splittedTask = task.split("-");
        String name = splittedTask[0];
        String ep = splittedTask[1];

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View catView = inflator.inflate(R.layout.task, null);
        TextView txtName = catView.findViewById(R.id.txtTaskName);
        txtName.setText(name);
        TextView txtEp = catView.findViewById(R.id.txtTaskEP);
        txtEp.setText(ep);
        return catView;
    }
}

