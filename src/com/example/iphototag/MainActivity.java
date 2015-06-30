package com.example.iphototag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony.Threads;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity implements LocationListener{

	public Button photoGalleryButton;
	public Button cameraButton;
	public Button infoButton;
	public static String EXTRA_MESSAGE;
	
	public TextView gpsStatus;
	public ImageView gpsStatusImage;
	
	public static double Latitude = 10;
	public static double Longitude = 10;
	public static int gps = 0;
	
	
	public LocationManager locationManager;
	public LocationListener locationListener;
	public Context context;
	
  	public static FeatureClasses featureClasses;
	

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = getApplicationContext();
 
        gpsStatus = (TextView) findViewById(R.id.gpsStatus2);
        gpsStatusImage =(ImageView) findViewById(R.id.gpsIcon);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		

        
        if(Latitude != 10 && Longitude != 10){
        	
        	gpsStatusImage.setImageResource(drawable.presence_online);

        }else{
        	gpsStatusImage.setImageResource(drawable.presence_busy);

        }

  
        photoGalleryButton = (Button) findViewById(R.id.galleryButton);
        cameraButton = (Button)findViewById(R.id.cameraButton);
        infoButton = (Button)findViewById(R.id.infoButton);
        
 
        photoGalleryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				openGallery(v);
			}
		});
        
        cameraButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				openCamera(v);
				
			}
		});
        
        
        infoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				infoBox(v);
				
			}
		});

     	featureClasses = new FeatureClasses();
	  	featureClasses.loadClasses();
	  
    }
    
    public void openCamera(View view){
    	Intent intent = new Intent(this, CameraActivity.class);
    	startActivity(intent);
    }
    
    public void openGallery(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    public void infoBox(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("iTagPhotos is an application dedicateted for taging location of photos , along with interest points ");
        
        //alertDialogBuilder.setIcon(icon.png);
        alertDialogBuilder.setTitle("Info");
        alertDialogBuilder.setPositiveButton("OK",null);
        
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
     }
     

    
    
    
    
    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onLocationChanged(Location location) {
		Latitude = location.getLatitude();
		Longitude = location.getLongitude();
		
		gpsStatusImage.setImageResource(drawable.presence_online);

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

    

}


