package com.example.to_do_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;


public class TaskListAdapter extends ArrayAdapter {

    private Context mContext;
    private int mResource;

    private static class ViewHolder {
        TextView titleView;
        Switch doneSwitch;
    }

    TaskListAdapter(Context context, int resource, List<Task> objects) {

        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,@NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        final Task task = (Task) getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            convertView = inflater.inflate(this.mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.doneSwitch = convertView.findViewById(R.id.switchDone);
            viewHolder.titleView = convertView.findViewById(R.id.titleOnEach);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.doneSwitch.setChecked(task.done);

        final View finalConvertView = convertView;
        viewHolder.doneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    task.done=true;
                    finalConvertView.setBackgroundResource(R.color.background);
                    viewHolder.titleView.setTextColor(Color.parseColor("#FFFFFF"));
                }else{
                    task.done=false;
                    finalConvertView.setBackgroundResource(R.color.white);
                    viewHolder.titleView.setTextColor(Color.parseColor("#006F75"));
                }
            }
        });

        if(task.done){
            finalConvertView.setBackgroundResource(R.color.background);
            viewHolder.titleView.setTextColor(Color.parseColor("#FFFFFF"));
        }else{
            finalConvertView.setBackgroundResource(R.color.white);
            viewHolder.titleView.setTextColor(Color.parseColor("#006F75"));
        }

        viewHolder.titleView.setText(task.getTitle());

        viewHolder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetailsActivity = new Intent(mContext, DetailsActivity.class);
                intentToDetailsActivity.putExtra("Details", task);
                Activity origin = (Activity)mContext;
                origin.startActivityForResult(intentToDetailsActivity,2);
            }
        });
        return finalConvertView;
    }
}
