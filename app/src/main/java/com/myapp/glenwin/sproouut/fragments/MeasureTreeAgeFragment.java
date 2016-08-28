package com.myapp.glenwin.sproouut.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.myapp.glenwin.sproouut.R;

/**
 * Created by Glenwin18 on 8/24/2016.
 */
public class MeasureTreeAgeFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_measure_tree_age, container, false);
        final EditText lengthInCm = (EditText)rootView.findViewById(R.id.edCm);
        final TextView age = (TextView) rootView.findViewById(R.id.edAge);
        lengthInCm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0){
                    age.setText(String.valueOf((int) ((Integer.parseInt(charSequence.toString())/3.1416) * 1.5)));
                }
                else{
                    age.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return rootView;
    }

}
