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
	        
	
	        
	        // Points of Interest
	        // catalogare ca si clase 
	        
	        TreeSet<String> typeNames = new TreeSet<String>();
	        for(PointOfInterest p: PhotoDisplayActivity.MyPointsOfInterest){
	        	typeNames.add(p.typeName);
	        }
	        
	    
	        
	  
	        int ok = 0;
	        int i = 1;
	        
	        for(String locationType : typeNames){
	        	
	        	if(locationType.contains("pharmacy")){
	        		Button btn = new Button(poiId.getContext());
	        		btn.setId(i);
	        		i++;
	        		btn.setLayoutParams(lparams);
	        		btn.setGravity(Gravity.CENTER);
	        		btn.setBackgroundResource(returnLayout2(i));
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
	        		btn.setId(i);
	        		i++;
	        		btn.setLayoutParams(lparams);
	        		btn.setGravity(Gravity.CENTER);
	        		btn.setBackgroundResource(returnLayout2(i));
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
		        		btn.setId(i);
		        		i++;
		        		btn.setLayoutParams(lparams);
		        		btn.setGravity(Gravity.CENTER);
		        		btn.setBackgroundResource(returnLayout2(i));
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
			        		btn.setId(i);
			        		i++;
			        		btn.setLayoutParams(lparams);
			        		btn.setGravity(Gravity.CENTER);
			        		btn.setBackgroundResource(returnLayout2(i));
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
	        			btn.setId(i);
	        			i++;
		        		btn.setLayoutParams(lparams);
		        		btn.setGravity(Gravity.CENTER);
		        		btn.setBackgroundResource(returnLayout2(i));
		        		btn.setText("Other");
		        		
		        		
		        		
		        	
		        		if(ok == 0){ // Ok -ul este ca sa audauge o singura data butonul others
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
	 
	        
	        
	        
	        // FindNearby - parsare si grupare dupa coduri 
	        
	        TreeSet<String> fcodesClassButtons = new TreeSet<String>();
	        for(FindNearbyContainer f : PhotoDisplayActivity.MyFindNearbyArray){
	        	fcodesClassButtons.add(f.fcode);
	        }
	        
	        int j = 1;
	        
	       i++;
	        for(String codeInClass : fcodesClassButtons){
	       
	            final FeatureCode auxF =  new FeatureCode();
	        	for(FeatureCode f : MainActivity.featureClasses.MyFeatureCodes){
	        		if(f.code.equals(codeInClass)/*&& f.className.equals("S")*/){
	        			auxF.code = f.code;
	        			auxF.description = f.description;
	        			auxF.className = f.className;
	        		}
	        	}
	        	
	        	Button btn = new Button(poiId.getContext());
        		btn.setId(i);
        		i++;
        		btn.setLayoutParams(lparams);
        		btn.setGravity(Gravity.CENTER);
        		btn.setBackgroundResource(returnLayout2(i));
        		j++;
        		if(j==10){
        			j=0;
        		}
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
	     if(!PhotoDisplayActivity.MyWikipedia.isEmpty()){   
	        Button btn = new Button(poiId.getContext());
    		btn.setId(i);
    		i++;
    		btn.setLayoutParams(lparams);
    		btn.setGravity(Gravity.CENTER);
    		btn.setBackgroundResource(returnLayout2(i));
    		
    		btn.setText("Wikipedia");
    		//reference = auxF.code;
    		btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
	        	        	callAbstractMap("wikipedia","wikipedia");
	        	        }
	        	 });
    		((LinearLayout)poiId).addView(btn);
	     }
	        
	        return v;
	}

	 public int returnLayout2(int i){
		 
		 int result = 0;
		 
		 if(i%2==1){
			 result = R.drawable.cafebuttontemplate;
		 }else{
			 result = R.drawable.wikipediabuttontemplate;
		 }
		 
		 
		 return result;
		 
		 
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
