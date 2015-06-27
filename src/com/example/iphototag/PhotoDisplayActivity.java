/**
 * 
 */
package com.example.iphototag;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geonames.Toponym;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author ocristea
 *
 */
public class PhotoDisplayActivity extends Activity {
	
	
	public Button arealButton;
	public Button bestFitButton;
	public static int position;
	
    public static ProgressDialog pDialog;
    
	
	public static ArrayList<FindNearbyContainer> MyFindNearbyArray ;
	public static ArrayList<PointOfInterest> MyPointsOfInterest ;
	public static ArrayList<WikipediaContainer> MyWikipedia;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_photo);

		arealButton =(Button)findViewById(R.id.arealButton);
		bestFitButton = (Button) findViewById(R.id.bestFitButton);
		
		

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
		
		
		position = getIntent().getExtras().getInt("imgPos");
    
		
		
		
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageBitmap(GalleryActivity.TaggedPhotosContainer.get(position).image);
		imageView.layout(0,0 , 0, 100);
		
		
		arealButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v ) {
	
				openAreal(v);
				
			}
		});
		
		double latitude = 0;
		double longitude = 0;
		
		ImageObject o = GalleryActivity.TaggedPhotosContainer.get(position);
		for(TagData t : o.tags){
			if(t.variableName.contains("atitude")){
				latitude = Double.parseDouble(t.variableValue);
			}
			if(t.variableName.contains("ongitude")){
				longitude = Double.parseDouble(t.variableValue);
			}
		}
		
		MyFindNearbyArray = new ArrayList<FindNearbyContainer>();
		MyPointsOfInterest = new ArrayList<PointOfInterest>();
		MyWikipedia = new ArrayList<WikipediaContainer>();
		
		GeonamesFunctions gn = new GeonamesFunctions();
		gn.findNearBy(latitude, longitude);
		gn.findNearbyPointsOfInterest(latitude, longitude);
		
		
	}   
	
	
    public void openAreal(View view) {
   
        Intent intent = new Intent(this, ArealActivity.class);
     
       
        startActivity(intent);
    }

}
