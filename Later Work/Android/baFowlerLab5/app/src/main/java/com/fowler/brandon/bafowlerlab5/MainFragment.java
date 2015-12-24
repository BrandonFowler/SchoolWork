//Name: Brandon Fowler
//Class: CSCD372(Android)
//Assignment: Lab5
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab5;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {

    private UpdateListener mListener;

    public interface UpdateListener {
        public void onFragmentInteraction(String string);
    }

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainFragment = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) mainFragment.findViewById(R.id.UpdateButton);
        button.setOnClickListener(this);
        return mainFragment;
    }

    public void onClick(View v) {
        if (mListener != null) {
            mListener.onFragmentInteraction(String.valueOf(System.currentTimeMillis()));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (UpdateListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement UpdateListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
