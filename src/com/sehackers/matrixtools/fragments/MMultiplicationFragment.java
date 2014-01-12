package com.sehackers.matrixtools.fragments;

import com.sehackers.matrixtools.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MMultiplicationFragment extends Fragment {
	
	public MMultiplicationFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_mmultiplication, container, false);
         
        return rootView;
    }
}
