package com.myapp.glenwin.sproouut.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapp.glenwin.sproouut.R;
import com.myapp.glenwin.sproouut.Report2;

/**
 * Created by Glenwin18 on 8/28/2016.
 */
public class ReportFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        Button btnStable = (Button)rootView.findViewById(R.id.btnStable);
        Button btnWarning = (Button)rootView.findViewById(R.id.btnWarning);
        Button btnDanger = (Button)rootView.findViewById(R.id.btnDanger);

        btnStable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Report2.class);
                intent.putExtra("status","0");
                startActivity(intent);

            }
        });

        btnWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Report2.class);
                intent.putExtra("status","1");
                startActivity(intent);

            }
        });

        btnDanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Report2.class);
                intent.putExtra("status","2");
                startActivity(intent);

            }
        });
        return rootView;
    }
}
