package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewTaskActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_task_layout);

        final EditText titleEdit = findViewById(R.id.titleEdit);
        final EditText descriptionEdit = findViewById(R.id.descriptionEdit);
        final EditText expirationDateEdit = findViewById(R.id.ExpirationDateEdit);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString();
                String description = descriptionEdit.getText().toString();
                String expirationDate = expirationDateEdit.getText().toString();

                if(title.equals("")){
                    Toast.makeText(NewTaskActivity.this,"Title must be filled",Toast.LENGTH_SHORT).show();
                }else if(expirationDate.equals("")){
                    Toast.makeText(NewTaskActivity.this,"Expiration date must be filled",Toast.LENGTH_SHORT).show();
                }else if(MainActivity.titles.contains(title)){
                    Toast.makeText(NewTaskActivity.this, "Title has been already existed", Toast.LENGTH_SHORT).show();
                }else{
                    Task newTask = new Task(title,description,expirationDate);
                    MainActivity.titles.add(newTask.getTitle());
                    Intent intent = new Intent();
                    intent.putExtra("New Task",newTask);
                    setResult(1,intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("New Task", (String) null);
        setResult(1,intent);
        finish();
    }
}
