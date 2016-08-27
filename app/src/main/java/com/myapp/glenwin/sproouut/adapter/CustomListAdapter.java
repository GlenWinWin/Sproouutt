package com.myapp.glenwin.sproouut.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.glenwin.sproouut.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Glenwin18 on 8/25/2016.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    private ArrayList<String> treeNames;
    private ArrayList<String> treePoints;
    private ArrayList<String> treeLockPics;
    private ArrayList<String> treeIds;

    public CustomListAdapter(Context context, ArrayList<String> treeNames, ArrayList<String> treePoints, ArrayList<String> treeLockPics, ArrayList<String> treeIds) {
        super(context,R.layout.list_trees,treeNames);
        mContext = context;
        this.treeNames = treeNames;
        this.treePoints = treePoints;
        this.treeLockPics = treeLockPics;
        this.treeIds = treeIds;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = inflater.inflate(R.layout.list_trees, null,false);
            TextView tvTreeName = (TextView) grid.findViewById(R.id.treeName);
            TextView tvPoint = (TextView)grid.findViewById(R.id.tvTreePoints);
            TextView tvId = (TextView)grid.findViewById(R.id.treeId);
            ImageView imageLockTree = (ImageView) grid.findViewById(R.id.ivTree);
            tvTreeName.setText(treeNames.get(position));
            tvPoint.setText(treePoints.get(position));
            tvId.setText(treeIds.get(position));
            Picasso.with(mContext)
                    .load(treeLockPics.get(position))
                    .into(imageLockTree);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }


}
