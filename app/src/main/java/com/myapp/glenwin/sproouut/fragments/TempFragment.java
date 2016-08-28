package com.myapp.glenwin.sproouut.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapp.glenwin.sproouut.R;

/**
 * Created by Glenwin18 on 8/28/2016.
 */
public class TempFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pop,null,false);
        Button btnS = (Button)rootView.findViewById(R.id.btnSe);
        btnS.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MeasureTreeAgeFragment llf = new MeasureTreeAgeFragment();
                ft.replace(R.id.content_frame_for_fragments, llf);
                ft.commit();
            }
        });
        return rootView;
    }
}
