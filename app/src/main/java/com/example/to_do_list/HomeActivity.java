package com.example.to_do_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<String> titles = new ArrayList<>();
    private TaskRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNewTaskActivity = new Intent(HomeActivity.this,NewTaskActivity.class);
                startActivityForResult(intentToNewTaskActivity,1);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.tasksList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new TaskRecyclerViewAdapter(this,tasks);
        DividerItemDecoration did = new DividerItemDecoration(recyclerView.getContext(),llm.getOrientation());
        recyclerView.addItemDecoration(did);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Task newTask =(Task) data.getSerializableExtra("New Task");
            if(newTask==null){
                return;
            }
            tasks.add(newTask);
            this.adapter.notifyItemInserted(tasks.size()-1);

        }if(requestCode==2){
            Task convertedTask = (Task)data.getSerializableExtra("converted task");
            int i = 0;
            for(; i < tasks.size() ; i++){
                if(tasks.get(i).getTitle().equals(convertedTask.getTitle())){
                    Task task = tasks.get(i);
                    task.done = convertedTask.done;
                    break;
                }
            }
            this.adapter.notifyItemChanged(i);
        }
    }
}


