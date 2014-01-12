package com.sehackers.matrixtools.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sehackers.matrixtools.R;

public class SingleMatrixInputFragment extends Fragment {
	
	public SingleMatrixInputFragment(){}
	
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_single_matrix_input, container, false);
        
        
//        Context context = getActivity();
//        CharSequence text = "Please enter your input";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
        
        return rootView;
    }
}
