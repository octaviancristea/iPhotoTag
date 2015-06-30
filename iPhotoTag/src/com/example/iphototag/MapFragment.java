package com.example.iphototag;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {

	MapView mMapView;
	private GoogleMap googleMap;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
   
		View v = inflater.inflate(R.layout.fragment_location_info, container,false);
		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);

		mMapView.onResume();
		
		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		googleMap = mMapView.getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		// latitude and longitude
    
		double latitude = 10;
		double longitude = 10;
		int position = PhotoDisplayActivity.position;
    
		ImageObject  o  = GalleryActivity.TaggedPhotosContainer.get(position);
		
		latitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, MainActivity.db.fieldLatitude));
		longitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, MainActivity.db.fieldLongitude));
    
	
		LatLng location = new LatLng(latitude,longitude);
    
		MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your location");
		
		marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
		googleMap.addMarker(marker);
		
		
		
		/*for(PointOfInterest p : PhotoDisplayActivity.MyPointsOfInterest){
			
			MarkerOptions  m = new MarkerOptions().position(new LatLng(Double.parseDouble(p.lat),Double.parseDouble(p.lng))).title(p.typeName+ " " +p.name);
			m.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			googleMap.addMarker(m);
		
			
		}*/
		
	
		CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
		

		googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14));

   
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}
}