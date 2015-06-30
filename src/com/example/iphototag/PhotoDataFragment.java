package com.example.iphototag;

import android.app.ActionBar.LayoutParams;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoDataFragment extends Fragment{
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
		

	    View v = new View(getActivity());
	    v =  inflater.inflate(R.layout.photo_data_fragment, container, false);
	    
	    View linear = v.findViewById(R.id.linear);
	
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		int position = PhotoDisplayActivity.position;
		
		ImageObject o = GalleryActivity.TaggedPhotosContainer.get(position);
		TextView tv;
		
		for(TagData t : o.tags){
		
			 tv= new TextView(linear.getContext());
			 tv.setLayoutParams(lparams);
			 tv.setTextSize(15);
			 tv.setTextColor(Color.rgb(75, 14, 14));
			 tv.setPadding(25, 6, 2, 2);
			 tv.setText(""+t.variableName+" : "+t.variableValue);
			 
			 ((LinearLayout)linear).addView(tv);
		}
		
	    return v;
		 
	 }
	

	
}
