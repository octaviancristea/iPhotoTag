/**
 * 
 */
package com.example.iphototag;

import android.app.Activity;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.*;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author ocristea
 *
 */
public class AbstractMap extends Activity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
    	//	position = getIntent().getExtras().getInt("imgPos");
    	
    	String key = getIntent().getExtras().getString("key");
    	String mapRequestType = getIntent().getExtras().getString("mapRequestType");
    	int position = PhotoDisplayActivity.position;
    	
    	
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
			

		
    	
    	
        LatLng MyPosition = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MyPosition, 16));
        
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your location");
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        
        map.addMarker(marker);
        
        if(mapRequestType.equals("poi")){
        	for(PointOfInterest p : PhotoDisplayActivity.MyPointsOfInterest){
			
			
        		if((p.typeName.contains(key))&& (!key.equals("other"))){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(p.lat),Double.parseDouble(p.lng))).title(p.typeName+ " " +p.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        			map.addMarker(m);
        		}
			
        		if(key.equals("other") && !p.typeName.contains("pharmacy")&& !p.typeName.contains("cafe")&& !p.typeName.contains("restaurant")&& !p.typeName.contains("bank")){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(p.lat),Double.parseDouble(p.lng))).title(p.typeName+ " " +p.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        			map.addMarker(m);
        		}
			
        	}
        }
        
        if(mapRequestType.equals("nearby")){
        	
        	for(FindNearbyContainer f : PhotoDisplayActivity.MyFindNearbyArray){
        	
        		if(f.fcode.equals(key)){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(f.lat),Double.parseDouble(f.lng))).title(f.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        			map.addMarker(m);
        		}
        		
        		
        	}
        	
        	
        	
        	
        	
        }
        
        
        
        
        
    }
}