package com.myapp.glenwin.sproouut.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapp.glenwin.sproouut.R;
import com.myapp.glenwin.sproouut.utils.Preferences;

/**
 * Created by Glenwin18 on 8/28/2016.
 */
public class AccumulateFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_accumulate,null,false);
        Button btn = (Button)rootView.findViewById(R.id.btnSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Code doesn't match");
                builder2.setMessage("I'm sorry your code doesn't match");
                builder2.setCancelable(true);
                builder2.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog2, int id) {
                                dialog2.cancel();
                            }
                        });
                AlertDialog alert12 = builder2.create();
                alert12.show();
            }
        });
        return rootView;
    }
}
