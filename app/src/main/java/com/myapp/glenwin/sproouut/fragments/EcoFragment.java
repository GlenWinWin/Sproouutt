package com.myapp.glenwin.sproouut.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.myapp.glenwin.sproouut.MainActivity;
import com.myapp.glenwin.sproouut.PlantsAnimals;
import com.myapp.glenwin.sproouut.R;

/**
 * Created by Glenwin18 on 8/28/2016.
 */
public class EcoFragment extends Fragment{
    ListView list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_2, container, false);
        list = (ListView)rootView.findViewById(R.id.listOfRegions);
        populateListView();
        registerClickCallBack();
        return rootView;
    }
    private void populateListView() {
        String[] regions = {"Region I \n(Ilocos Region)", "Region II \n(Cagayan Valley)", "Region III \n(Central Luzon)", "Region IV-A \n(CALABARZON)",
                "Region IV-B \n(MIMAROPA)", "Region V \n(Bicol Region)", "Region VI \n(Western Visayas)", "Region VII \n(Central Visayas)",
                "Region VIII \n(Eastern Visayas)", "Region IX \n(Zamboanga Peninsula)", "Region X \n(Northern Mindanao)", "Region XI \n(Davao Region)",
                "Region XII \n(Soccsksargen)", "Region XIII \n(Caraga)", "Region XVIII \n(Negros Island Region)", "A.R.M.M. \n(Autonomous Region in Muslim Mindanao)", "C.A.R. \n(Cordillera Administrative Region)"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.data_regions, regions);

        list.setAdapter(adapter);
    }

    private void registerClickCallBack() {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Intent intent = new Intent(getActivity(), PlantsAnimals.class);
                startActivity(intent);
            }
        });


    }
}
