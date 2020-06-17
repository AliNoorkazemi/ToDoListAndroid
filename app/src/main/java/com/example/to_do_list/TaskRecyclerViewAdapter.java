package com.example.to_do_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class TaskRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<Task> list;
    private Context mContext;

    TaskRecyclerViewAdapter(Context context,List<Task> list){
        this.list = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_each_list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final TaskViewHolder tvh = (TaskViewHolder)holder;
        final Task task = list.get(position);
        tvh.bind(task);

        final View finalConvertView = tvh.itemView.findViewById(R.id.itemView);

        if(task.done){
            tvh.doneSwitch.setChecked(true);
            finalConvertView.setBackgroundResource(R.color.background);
            tvh.titleView.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            tvh.doneSwitch.setChecked(false);
            finalConvertView.setBackgroundResource(R.color.white);
            tvh.titleView.setTextColor(Color.parseColor("#006F75"));
        }


        tvh.doneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    task.done=true;
                    finalConvertView.setBackgroundResource(R.color.background);
                    tvh.titleView.setTextColor(Color.parseColor("#FFFFFF"));
                }else{
                    task.done=false;
                    finalConvertView.setBackgroundResource(R.color.white);
                    tvh.titleView.setTextColor(Color.parseColor("#006F75"));
                }
            }
        });

        tvh.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetailsActivity = new Intent(mContext, DetailsActivity.class);
                intentToDetailsActivity.putExtra("Details", task);
                Activity origin = (Activity)mContext;
                origin.startActivityForResult(intentToDetailsActivity,2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        Switch doneSwitch;

        TaskViewHolder(View itemView){
            super(itemView);
            titleView = itemView.findViewById(R.id.titleOnEach);
            doneSwitch = itemView.findViewById(R.id.switchDone);
        }

        void bind(Task task) {
            titleView.setText(task.getTitle());
        }

    }
}
