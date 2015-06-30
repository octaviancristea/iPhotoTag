/**
 * 
 */
package com.example.iphototag;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author ocristea
 *
 */
public class MarkerActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.marker_layout);
		
		//position = getIntent().getExtras().getInt("imgPos");
		
		String mapRequestType = getIntent().getExtras().getString("mapRequestType");
		String markerTitle = getIntent().getExtras().getString("markerName");
		
		LinearLayout linear = (LinearLayout)findViewById(R.id.markerliniar);
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		
		TextView tv = null;
		// tv.setLayoutParams(lparams);
		// tv.setTextSize(15);
		// tv.setTextColor(Color.rgb(75, 14, 14));
		// tv.setPadding(25, 6, 2, 2);
		// #2F0404
		if(mapRequestType.equals("poi")){ // p.name
			
			PointOfInterest P = new PointOfInterest();
			for(PointOfInterest p :  PhotoDisplayActivity.MyPointsOfInterest ){
				if(p.name.equals(markerTitle)){
					P = p;
				}
			
			}
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(P.typeName);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(P.name);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Latitude : "+P.lat);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Longitude: "+P.lng);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Distance : "+P.distance);
			((LinearLayout)linear).addView(tv);
			
			
			
		}
		
		if(mapRequestType.equals("nearby")){ // f.names
			
			FindNearbyContainer F = new FindNearbyContainer();
			for(FindNearbyContainer f : PhotoDisplayActivity.MyFindNearbyArray){
				if(f.name.equals(markerTitle)){
					F = f;
				}
			}
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(F.toponymName);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(F.name);
			((LinearLayout)linear).addView(tv);
			
			
		
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Latitude : "+F.lat);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Longitude: "+F.lng);
			((LinearLayout)linear).addView(tv);
			
			
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Distance : "+F.distance);
			((LinearLayout)linear).addView(tv);
			
			
			
		}
		
		
		if(mapRequestType.equals("wikipedia")){ // w.title
			
			WikipediaContainer W = new WikipediaContainer();
			for(WikipediaContainer w : PhotoDisplayActivity.MyWikipedia){
				if(w.title.equals(markerTitle)){
					W = w;
				}
			}
			
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(W.title);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(25);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Summary : ");
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(W.summary);
			((LinearLayout)linear).addView(tv);
			
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText(Html.fromHtml( "<a href="+W.wikipediaUrl +">link</a> "));
		    tv.setMovementMethod(LinkMovementMethod.getInstance());
			((LinearLayout)linear).addView(tv);
			
			
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Latitude : "+W.lat);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Longitude: "+W.lng);
			((LinearLayout)linear).addView(tv);
			
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			 tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Distance : "+W.distance);
			((LinearLayout)linear).addView(tv);
			
			tv = new TextView(linear.getContext());
			tv.setLayoutParams(lparams);
			tv.setPadding(25, 6, 2, 2);
			tv.setTextSize(20);
			tv.setTextColor(Color.parseColor("#2F0404"));
			tv.setText("Rank : "+W.rank);
			((LinearLayout)linear).addView(tv);
			
			
			
		}
		
	
		
		
		
	}
	
	
	

}
