package com.example.codetheoyeucau_thietke_list_task_task_completed;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private List<Task>  taskList;
    private IListenerClickCheckBox iListenerClickCheckBox;

    //sd interface callback ra been ngoai
    public interface IListenerClickCheckBox{
        void onClickCheckBox(Task task , int position);
    }

    void setData(List<Task> list, IListenerClickCheckBox iListenerClickCheckBox){
        this.taskList=list;
        this.iListenerClickCheckBox= this.iListenerClickCheckBox;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new TaskViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder,  int position) {
         Task task = taskList.get(position);
        if(task==null){
            return;
        }
        holder.tvTask.setText(task.getName());
        holder.chbComplete.setChecked(task.isCompleted());

        holder.chbComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("AAA",task.toString());
                Log.d("BBB",task.toString());
//                iListenerClickCheckBox.onClickCheckBox(task,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(taskList!=null){
            return taskList.size();
        }
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        private CheckBox chbComplete;
        private TextView tvTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            chbComplete = itemView.findViewById(R.id.chb_complete);
            tvTask      = itemView.findViewById(R.id.tv_task);
        }
    }
}
