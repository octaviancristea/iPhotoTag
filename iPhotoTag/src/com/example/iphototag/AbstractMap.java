/**
 * 
 */
package com.example.iphototag;

import android.app.Activity;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author ocristea
 *
 */
public class AbstractMap extends Activity implements OnMapReadyCallback  {

	 public MapFragment mapFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        /*MapFragment*/ mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        
     
        mapFragment.getMapAsync(this);
        
    }

    @Override
    public void onMapReady(GoogleMap map){
    	//	position = getIntent().getExtras().getInt("imgPos");
    	
    	String key = getIntent().getExtras().getString("key");
    	final String mapRequestType = getIntent().getExtras().getString("mapRequestType");
    	int position = PhotoDisplayActivity.position;
    	
    	
    	double latitude = 0;
		double longitude = 0;
		
		ImageObject o = GalleryActivity.TaggedPhotosContainer.get(position);
	
		latitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, MainActivity.db.fieldLatitude));
		longitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, MainActivity.db.fieldLongitude));
		
    	
    	
        LatLng MyPosition = new LatLng(latitude, longitude);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MyPosition, 16));
        
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your location");
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        
        map.addMarker(marker);
        
        if(mapRequestType.equals("poi")){
        	for(PointOfInterest p : PhotoDisplayActivity.MyPointsOfInterest){
			
			
        		if((p.typeName.contains(key))&& (!key.equals("other"))){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(p.lat),Double.parseDouble(p.lng))).title(p.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        			map.addMarker(m);
        		}
			
        		if(key.equals("other") && !p.typeName.contains("pharmacy")&& !p.typeName.contains("cafe")&& !p.typeName.contains("restaurant")&& !p.typeName.contains("bank")){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(p.lat),Double.parseDouble(p.lng))).title(p.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        			map.addMarker(m);
        		}
			
        	}
        }
        
        if(mapRequestType.equals("nearby")){
        	
        	for(FindNearbyContainer f : PhotoDisplayActivity.MyFindNearbyArray){
        	
        		if(f.fcode.equals(key)){
        		
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(f.lat),Double.parseDouble(f.lng))).title(f.name);
        			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        			map.addMarker(m);
        		}
        		
        		
        	}
        	
        	
        	
        	
        	
        }
        
        if(mapRequestType.equals("wikipedia")){
        	
        	for(WikipediaContainer w : PhotoDisplayActivity.MyWikipedia){
        		
        		if(!w.feature.contains("adm") && Integer.parseInt(w.rank) > 50){
        			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(w.lat),Double.parseDouble(w.lng))).title(w.title);
    				m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
    				map.addMarker(m);
    			}
        	
        	}
        	
        }
        
       map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
		
    	   @Override
			public boolean onMarkerClick(Marker marker) {
    		   Intent i = new Intent(mapFragment.getActivity(), MarkerActivity.class);
               
               i.putExtra("mapRequestType", mapRequestType);
               i.putExtra("markerName", marker.getTitle());
               
               startActivity(i);

			return false;
		}
       	});
    	   
       }
        

   


}