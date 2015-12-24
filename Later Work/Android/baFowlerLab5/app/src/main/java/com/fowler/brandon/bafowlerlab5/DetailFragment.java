//Name: Brandon Fowler
//Class: CSCD372(Android)
//Assignment: Lab5
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab5;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    String updateText;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textView = (TextView) v.findViewById(R.id.UpdateText);
        if (getArguments() != null){
            String updateText = getArguments().getString("UPDATE_TEXT");
            textView.setText(updateText);
        }
        else if(this.updateText != null){
            textView.setText(this.updateText);
        }
        return v;
    }

    public void setUpdateText(String updateText){
        this.updateText = updateText;
        if(getView() != null) {
            TextView textView = (TextView) getView().findViewById(R.id.UpdateText);
            textView.setText(updateText);
        }
    }
}
