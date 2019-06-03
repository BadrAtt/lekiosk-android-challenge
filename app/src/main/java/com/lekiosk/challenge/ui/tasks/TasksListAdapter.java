package com.lekiosk.challenge.ui.tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lekiosk.challenge.R;
import com.lekiosk.challenge.models.Tache;
import java.util.List;

/**
 * Created by Badr Elattaoui
 * on 03/06/2019.
 */

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.TaskViewHolder> {

    private List<Tache> mTasksList;


    public TasksListAdapter(List<Tache> mTasksList) {
        this.mTasksList = mTasksList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TaskViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        Tache tache = mTasksList.get(i);
        taskViewHolder.mTaskTitle.setText(tache.getmTitle());
        if(!tache.ismCompleted()){
            taskViewHolder.mTaskStaeIcon.setImageResource(R.drawable.ic_check);
        }
    }

    @Override
    public int getItemCount() {
        return mTasksList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView mTaskTitle;
        private ImageView mTaskStaeIcon;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTaskTitle = itemView.findViewById(R.id.title);
            mTaskStaeIcon = itemView.findViewById(R.id.task_state_ico);
        }
    }
}
