package com.myapp.glenwin.sproouut.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapp.glenwin.sproouut.R;

/**
 * Created by Glenwin18 on 8/24/2016.
 */
public class MeasureTreeAgeFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_measure_tree_age, container, false);

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.pop);
        dialog.setTitle("Select Specie");


        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return rootView;
    }

}
