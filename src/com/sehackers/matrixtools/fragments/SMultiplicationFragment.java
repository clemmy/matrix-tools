package com.sehackers.matrixtools.fragments;

import com.sehackers.matrixtools.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SMultiplicationFragment extends Fragment {
	
	public SMultiplicationFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_smultiplication, container, false);
         
        return rootView;
    }
}
