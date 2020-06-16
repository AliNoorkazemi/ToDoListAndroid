package com.example.to_do_list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<String> titles = new ArrayList<>();
    private TaskListAdapter taskListAdapter;
    private RecyclerView recyclerView;
    private TaskRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToNewTaskActivity = new Intent(MainActivity.this,NewTaskActivity.class);
                startActivityForResult(intentToNewTaskActivity,1);
            }
        });

        recyclerView = findViewById(R.id.tasksList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new TaskRecyclerViewAdapter(MainActivity.this,tasks);
        recyclerView.setAdapter(adapter);


//        ListView listView = findViewById(R.id.listView);
//        this.taskListAdapter = new TaskListAdapter(this,R.layout.on_each_list_item,tasks);
//        listView.setAdapter(taskListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Task newTask =(Task) data.getSerializableExtra("New Task");
            tasks.add(newTask);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new TaskRecyclerViewAdapter(MainActivity.this,tasks);
            recyclerView.setAdapter(adapter);
//            this.adapter.notifyDataSetChanged();
        }if(requestCode==2){
            Task convertedTask = (Task)data.getSerializableExtra("converted task");
            for(int i = 0 ; i < tasks.size() ; i++){
                if(tasks.get(i).getTitle().equals(convertedTask.getTitle())){
                    Task task = tasks.get(i);
                    task.done = convertedTask.done;
                    break;
                }
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter = new TaskRecyclerViewAdapter(MainActivity.this,tasks);
            recyclerView.setAdapter(adapter);
//            this.adapter.notifyDataSetChanged();
        }
    }
}
