package com.myapp.glenwin.sproouut;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListWithPicturesPlants extends ListActivity {
    int[] drawablesPlant = {R.drawable.balayog, R.drawable.balitbitan, R.drawable.banuyo, R.drawable.bayagusa, R.drawable.lamog,
            R.drawable.malakmalak, R.drawable.pili, R.drawable.potat, R.drawable.santiki,
            R.drawable.tamayuan, R.drawable.toog};
    String[] namePlant = {"Balayong", "Balibitan", "Banuyo", "Lamog",
            "Malak-malak", "Pili", "Potat", "Santiki Paitan", "Tamayuan", "Toog"};


    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_with_pictures);

        CustomAdapter adapter = new CustomAdapter(this, namePlant, drawablesPlant);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        String value = l.getItemAtPosition(position).toString();
        Integer integer = 0;


        Intent intent = new Intent(getApplication(), SubDetailsPlants.class);
        intent.putExtra(integer.toString(), "positions");
        startActivity(intent);

    }
}