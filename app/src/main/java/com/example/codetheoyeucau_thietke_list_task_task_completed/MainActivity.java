package com.example.codetheoyeucau_thietke_list_task_task_completed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvTask;
    private RecyclerView rcvTaskCompleted;
    private TextView tvCountTaskCompleted;
    private ImageView img1;
    private LinearLayout layoutTaskCompleted;

    private TaskAdapter taskAdapter;
    private TaskAdapter taskAdapter_Completed;

    private List<Task> mListTask;
    private List<Task> mListTask_Completed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        setData_ListTask();
        setData_ListTask_Completed();

    }
    private void setData_ListTask(){
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvTask.setLayoutManager(linearLayoutManager1);
        rcvTask.setNestedScrollingEnabled(false);
        rcvTask.setFocusable(false);

        taskAdapter = new TaskAdapter();
        mListTask=getListTask();

        taskAdapter.setData(mListTask, new TaskAdapter.IListenerClickCheckBox() {
            @Override
            public void onClickCheckBox(Task task, int position) {
                // khi ma hoan thanh thì mk phải remove cái task mk đi
                mListTask.remove(task);
                 taskAdapter.notifyItemRemoved(position);
                taskAdapter.notifyItemRangeRemoved(position,mListTask.size());

                task.setCompleted(true);
                mListTask_Completed.add(task);
                taskAdapter_Completed.notifyDataSetChanged();
            }
        });
        rcvTask.setAdapter(taskAdapter);
    }

    private void setData_ListTask_Completed(){
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        rcvTaskCompleted.setLayoutManager(linearLayoutManager1);
        rcvTaskCompleted.setNestedScrollingEnabled(false);
        rcvTaskCompleted.setFocusable(false);

        taskAdapter_Completed = new TaskAdapter();
        taskAdapter_Completed.setData(mListTask_Completed, new TaskAdapter.IListenerClickCheckBox() {  // đâu tiên chưa có cai list  nào hoàn thành nên mình sẽ truyền cai list rỗng vào

            @Override
            public void onClickCheckBox(Task task, int position) {
                mListTask_Completed.remove(task);
                taskAdapter_Completed.notifyItemRemoved(position);
                taskAdapter_Completed.notifyItemRangeRemoved(position,mListTask_Completed.size());

                task.setCompleted(false);
                mListTask.add(task);
                taskAdapter.notifyDataSetChanged();
            }
        });
        rcvTaskCompleted.setAdapter(taskAdapter_Completed);
    }

    private List<Task> getListTask(){
        List<Task> list = new ArrayList<>();
        list.add(new Task("Task 1 ",false));
        list.add(new Task("Task 2 ",false));
        list.add(new Task("Task 3 ",false));
        list.add(new Task("Task 4 ",false));
        return list;
    }


    private void AnhXa() {
        rcvTask = findViewById(R.id.rcv_task);
        rcvTaskCompleted = findViewById(R.id.rcv_task_completed);
        tvCountTaskCompleted = findViewById(R.id.txt_Count_ask_completed);
        img1 = findViewById(R.id.img1);
        layoutTaskCompleted = findViewById(R.id.layout_task_completed);
    }
}