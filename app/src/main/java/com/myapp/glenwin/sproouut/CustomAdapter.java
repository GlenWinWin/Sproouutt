package com.myapp.glenwin.sproouut;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Charlotte on 8/27/2016.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    Context c;
    String[] data_names;
    int[] data_images;
    LayoutInflater inflater;
    //int[] data_id;

    public CustomAdapter(Context context, String[] data_names, int[] data_images) {
        super(context, R.layout.data_with_pics, data_names);

        this.c=context;
        this.data_names=data_names;
        this.data_images=data_images;
        //this.data_id=data_id;
    }

    public class ViewHolder{

        TextView nameTv;
        ImageView img;
        //TextView id;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.data_with_pics, null);
        }
        ViewHolder holder = new ViewHolder();
        holder.nameTv = (TextView) convertView.findViewById(R.id.data_names);
        holder.img = (ImageView) convertView.findViewById(R.id.data_pictures);
        //holder.id = (TextView) convertView.findViewById(R.id.textView6);

        holder.nameTv.setText(data_names[position]);
        holder.img.setImageResource(data_images[position]);
//        holder.id.setText(data_id[position]);
        return convertView;
    }
}
