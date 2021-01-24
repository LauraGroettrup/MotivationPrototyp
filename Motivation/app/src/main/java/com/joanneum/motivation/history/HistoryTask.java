package com.joanneum.motivation.history;

import java.util.ArrayList;

public class HistoryTask {
    private String tasks;
    private String date;

    public HistoryTask(String date, String tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public String getTasks() {
        return tasks;
    }

    public String getDate() {
        return date;
    }

    public static ArrayList<HistoryTask> createTaskList(String preferenceTasks) {
        ArrayList<HistoryTask> doneTasks = new ArrayList<HistoryTask>();

        //format: 12.02.2020:Dishes;01.03.2020:pets;
        String[] allTasks = preferenceTasks.split(";");
        for (int i = 0; i < allTasks.length; i++) {
            String[] oneTask = allTasks[i].split(":");
            doneTasks.add(new HistoryTask(oneTask[0], oneTask[1]));
        }

        return doneTasks;
    }
}
