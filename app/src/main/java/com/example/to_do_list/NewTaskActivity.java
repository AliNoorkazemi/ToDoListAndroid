package com.example.to_do_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private DatePickerDialog datePicker;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_task_layout);

        final EditText titleEdit = findViewById(R.id.titleEdit);
        final EditText descriptionEdit = findViewById(R.id.descriptionEdit);
        final EditText expirationDateEdit = findViewById(R.id.ExpirationDateEdit);
        expirationDateEdit.setInputType(InputType.TYPE_NULL);
        expirationDateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(NewTaskActivity.this , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        expirationDateEdit.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year, month, day);

                datePicker.show();
            }
        });

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
                }else if(HomeActivity.titles.contains(title)){
                    Toast.makeText(NewTaskActivity.this, "Title has been already existed", Toast.LENGTH_SHORT).show();
                }else{
                    DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
                    Date date = null;
                    try {
                        date = formatter.parse(expirationDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Task newTask = new Task(title,description,false,date);
                    HomeActivity.titles.add(newTask.getTitle());
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
