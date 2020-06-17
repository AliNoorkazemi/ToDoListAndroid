package com.example.to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetailsActivity extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        Intent intent=getIntent();
        this.task = (Task) intent.getSerializableExtra("Details");


        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView expirationDate = findViewById(R.id.ExpirationDate);
        Switch doneSwitch = findViewById(R.id.SwitchInDetails);
        DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String expirationDateString = formatter.format(task.getDate());

        title.setText("Title :"+"\n"+task.getTitle());
        description.setText("Description :"+"\n"+task.getDescription());
        expirationDate.setText("Expiration Date :"+"\n"+expirationDateString);

        doneSwitch.setChecked(task.done);

        doneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    task.done = true;
                }else{
                    task.done = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("converted task",task);
        setResult(2,intent);
        finish();
    }
}
