package com.sehackers.matrixtools.fragments;

import com.sehackers.matrixtools.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdditionFragment extends Fragment {
	
	public AdditionFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_addition, container, false);
        
//        FragmentManager fragmentManager = getFragmentManager();
//        Fragment x = new SingleMatrixInputFragment();
//		  fragmentManager.beginTransaction()
//				.replace(R.id.addition_content_frame, x).commit();
        
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, new SingleMatrixInputFragment()).commit();
         
        return rootView;
    }
}
