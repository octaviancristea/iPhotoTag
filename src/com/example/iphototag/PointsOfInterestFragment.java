package com.example.iphototag;

import java.util.TreeSet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

public class PointsOfInterestFragment extends Fragment{
	
	public static String reference;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		 View v = new View(getActivity()); 
		 v = inflater.inflate(R.layout.poi_fragment, container, false);
	        
	        View poiId = (LinearLayout)v.findViewById(R.id.poiID);
	        
	        LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	        TextView tv;
	        
	        TreeSet<String> typeNames = new TreeSet<String>();
	        for(PointOfInterest p: PhotoDisplayActivity.MyPointsOfInterest){
	        	typeNames.add(p.typeName);
	        }
	        
	    
	        
	        //Button btn;
	        int ok = 0;
	        int i = 0;
	        
	        for(String locationType : typeNames){
	        	
	        	if(locationType.contains("pharmacy")){
	        		Button btn = new Button(poiId.getContext());
	        		btn.setId(i+1);
	        		btn.setLayoutParams(lparams);
	        		btn.setGravity(Gravity.CENTER);
	        		btn.setBackgroundResource(R.drawable.pharmacybuttontemplate);
	        		btn.setText("Pharmacy");
	        		
	        		btn.setOnClickListener(new OnClickListener() {
	        	        public void onClick(View v) {
	        	        	callAbstractMap("pharmacy","poi");
	        	        }
	        	    });
	        		
	    
	        		
	        		((LinearLayout)poiId).addView(btn);
	        	}else
	        	
	        	if(locationType.contains("restaurant")){
	        		Button btn = new Button(poiId.getContext());
	        		btn.setId(i+1);
	        		btn.setLayoutParams(lparams);
	        		btn.setGravity(Gravity.CENTER);
	        		btn.setBackgroundResource(R.drawable.restaurantbuttontemplate);
	        		btn.setText("Restaurant");
	        		
	        		btn.setOnClickListener(new OnClickListener() {
	        	        public void onClick(View v) {
	        	        	callAbstractMap("restaurant","poi");
	        	        }
	        	    });
	        	
	      
	        		((LinearLayout)poiId).addView(btn);
	        	}else
	        		
	        		if(locationType.contains("cafe")){
		        		Button btn = new Button(poiId.getContext());
		        		btn.setId(i+1);
		        		btn.setLayoutParams(lparams);
		        		btn.setGravity(Gravity.CENTER);
		        		btn.setBackgroundResource(R.drawable.cafebuttontemplate);
		        		btn.setText("Cafe");
		        		
		        		btn.setOnClickListener(new OnClickListener() {
		        	        public void onClick(View v) {
		        	        	callAbstractMap("cafe","poi");
		        	        }
		        	    });
		        	
		        	
		        		((LinearLayout)poiId).addView(btn);
	        		}else
	        			if(locationType.contains("bank")){
			        		Button btn = new Button(poiId.getContext());
			        		btn.setId(i+1);
			        		btn.setLayoutParams(lparams);
			        		btn.setGravity(Gravity.CENTER);
			        		btn.setBackgroundResource(R.drawable.bankbuttontemplate);
			        		btn.setText("Bank");
			        		
			        		btn.setOnClickListener(new OnClickListener() {
			        	        public void onClick(View v) {
			        	        	callAbstractMap("bank","poi");
			        	        }
			        	    });
			        	
			        	
			        		((LinearLayout)poiId).addView(btn);
		        		}else
	        		
	        		{
	        			Button btn = new Button(poiId.getContext());
	        			btn.setId(i+1);
		        		btn.setLayoutParams(lparams);
		        		btn.setGravity(Gravity.CENTER);
		        		btn.setBackgroundResource(R.drawable.otherbuttontemplate);
		        		btn.setText("Other");
		        		
		        		
		        		
		        	
		        		if(ok == 0){
		        			btn.setOnClickListener(new OnClickListener() {
			        	        public void onClick(View v) {
			        	        	callAbstractMap("other","poi");
			        	        }
			        	    });
		        			
		        			
		        			((LinearLayout)poiId).addView(btn);
		        		}
		        		ok = 1;
	        		}
	        	
	        	
	        	
	        }
	        // add FindNearby
	        //PhotoDisplayActivity.MyFindeNearbyArray
	    	//lng 
	    	//lat;
	    	//distance;
	    	//geonameId;
	    	//toponymName;
	    	//name;
	    	//fcode;
	    	//fcl;
	        //MainActivity.featureClasses
	       // TreeSet<String> typeNames = new TreeSet<String>();
	       // for(PointOfInterest p: PhotoDisplayActivity.MyPointsOfInterest){
	       // 	typeNames.add(p.typeName);
	       // }
	        TreeSet<String> fcodesClassButtons = new TreeSet<String>();
	        for(FindNearbyContainer f : PhotoDisplayActivity.MyFindNearbyArray){
	        	fcodesClassButtons.add(f.fcode);
	        }
	        
	        int j = 1;
	        
	       
	        for(String codeInClass : fcodesClassButtons){
	       
	        	final FeatureCode auxF =  new FeatureCode();
	        	for(FeatureCode f : MainActivity.featureClasses.MyFeatureCodes){
	        		if(f.code.equals(codeInClass)&& f.className.equals("S")){
	        			auxF.code = f.code;
	        			auxF.description = f.description;
	        			auxF.className = f.className;
	        		}
	        	}
	        	
	        	Button btn = new Button(poiId.getContext());
        		btn.setId(i+1);
        		btn.setLayoutParams(lparams);
        		btn.setGravity(Gravity.CENTER);
        		btn.setBackgroundResource(returnLayout(j));
        		j++;
        		btn.setText(auxF.description);
        		//reference = auxF.code;
        		btn.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
		        	        	callAbstractMap(auxF.code,"nearby");
		        	        }
		        	 });
        	       
        		((LinearLayout)poiId).addView(btn);
	        	
	        }
	        
	        //add Wikipedia Button
	        return v;
	    }

	 public int returnLayout(int j){
		 int result = 0;
		 
		 if(j==1){
			 result = R.drawable.button1;
		 }
		 if(j==2){
			 result = R.drawable.button2;
		 }
		 if(j==3){
			 result = R.drawable.button3;
		 }
		 if(j==4){
			 result = R.drawable.button4;
		 }
		 if(j==5){
			 result = R.drawable.button5;
		 }
		 if(j==6){
			 result = R.drawable.button6;
		 }
		 if(j==7){
			 result = R.drawable.button7;
		 }
		 if(j==8){
			 result = R.drawable.button8;
		 }
		 if(j==9){
			 result = R.drawable.button9;
		 }
		 if(j==10){
			 result = R.drawable.button10;
		 }
		 
		 
		 return result;
	 }
	 
	 public void callAbstractMap(String whatToCallFor,String mapType){
		 Intent intent = new Intent(getActivity() ,AbstractMap.class);
		 intent.putExtra("key",whatToCallFor);
		 if(whatToCallFor.equals("1")){
        	 intent.putExtra("key",reference);
         }
         intent.putExtra("mapRequestType", mapType);
         startActivity(intent);
	 }
	 
}
