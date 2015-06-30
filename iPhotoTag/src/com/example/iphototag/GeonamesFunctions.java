package com.example.iphototag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;



public class GeonamesFunctions {
	
  
    public static String TAG = MainActivity.class.getSimpleName();
    public String jsonResponse;
 
    
 
	
    public void findNearBy(double latitude, double longitude) {
    	 
    	if(latitude != 0 && longitude !=0 ){
    	
    	showpDialog();
    	
    	String urlJsonObj = "http://api.geonames.org/findNearbyJSON?lat="+latitude+"&lng="+longitude+"&username=ocristea&radius=1";
       
    	
    	
    	 JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
    	            urlJsonObj, null, new Response.Listener<JSONObject>() {
    	 
    	                @Override
    	                public void onResponse(JSONObject response) {
                       
                        try {
                        	
                        	
                  
                        	JSONArray response2 = response.getJSONArray("geonames");
                           
                        	jsonResponse = "";
                            for (int i = 0; i < response2.length(); i++) {
 
                                JSONObject location = (JSONObject) response2.get(i);
 
                                FindNearbyContainer f = new FindNearbyContainer();
                                
                                f.lng = location.getString("lng");
                                f.lat = location.getString("lat");
                                f.distance = location.getString("distance");
                                f.geonameId = location.getString("geonameId");
                                f.toponymName = location.getString("toponymName");
                                f.name = location.getString("name");
                                f.fcode =location.getString("fcode");
                                f.fcl =location.getString("fcl");
                               
                                PhotoDisplayActivity.MyFindNearbyArray.add(f);
 
                   
                            }
 
                            try {
                            	PhotoDisplayActivity.fileExists(PhotoDisplayActivity.FNBFileName);
								PhotoDisplayActivity.writeFNBArray(PhotoDisplayActivity.FNB, PhotoDisplayActivity.MyFindNearbyArray );
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
 
                        } catch (JSONException e) {
                            e.printStackTrace();
      
                        }
 
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    	 hidepDialog();
                    }
                });
 
   
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    	}else{
    		
    	}
    	
    
    }
    
    public void findNearbyPointsOfInterest(double latitude, double longitude) {
   	 
    	if(latitude != 0 && longitude !=0 ){
    	
    	showpDialog();
    	
    	String urlJsonObj = "http://api.geonames.org/findNearbyPOIsOSMJSON?lat="+latitude+"&lng="+longitude+"&username=ocristea&radius=1";
       
    	
    	
    	JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
	            urlJsonObj, null, new Response.Listener<JSONObject>() {
	 
	                @Override
	                public void onResponse(JSONObject response) {
                   
                    try {
                    	
                    	
                  
                    		JSONArray response2 = response.getJSONArray("poi");
                            jsonResponse = "";
                            for (int i = 0; i < response2.length(); i++) {
 
                                JSONObject location = (JSONObject) response2.get(i);

                                PointOfInterest p = new PointOfInterest();
                                
                                p.lng = location.getString("lng");
                                p.lat = location.getString("lat");
                                p.distance = location.getString("distance");
                                p.name = location.getString("name");
                                p.typeName =location.getString("typeName");
 
                                PhotoDisplayActivity.MyPointsOfInterest.add(p);
                                Log.d("Android :",p.name);
                   
                            }
                            try {
                            	PhotoDisplayActivity.fileExists(PhotoDisplayActivity.FNBPFileName);
								PhotoDisplayActivity.writeFNBPArray(PhotoDisplayActivity.FNBP, PhotoDisplayActivity.MyPointsOfInterest );
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
 
                        
 
                        } catch (JSONException e) {
                            e.printStackTrace();
      
                        }
 
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    	 hidepDialog();
                    }
                });
 
   
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    	}else{
    		
    	}
   
    }
    
    
    public void findNearWikipedia(double latitude, double longitude) {
   	 
    	if(latitude != 0 && longitude !=0 ){
    	
    	showpDialog();
    	
    	//http://api.geonames.org/findNearbyWikipedia?lat=47.15917&lng=27.58517&username=ocristea&lang=en
    	String urlJsonObj = "http://api.geonames.org/findNearbyWikipediaJSON?lat="+latitude+"&lng="+longitude+"&username=ocristea&lang=en";
       
    	
    	JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
	            urlJsonObj, null, new Response.Listener<JSONObject>() {
	 
	                @Override
	                public void onResponse(JSONObject response) {
                   
                    try {
                    	
                    	
                        // Parsing json array response
                        // loop through each json object
                    		JSONArray response2 = response.getJSONArray("geonames");
                            jsonResponse = "";
                            for (int i = 0; i < response2.length(); i++) {
 
                                JSONObject location = (JSONObject) response2.get(i);
                                
                                WikipediaContainer w = new WikipediaContainer();
                                w.lat = location.getString("lat");
                                w.lng = location.getString("lng");
                             
                                w.title = location.getString("title");
                                w.rank = location.getString("rank");
                                w.summary = location.getString("summary");
                                w.distance =location.getString("distance");
                                w.wikipediaUrl = location.getString("wikipediaUrl");
                                if(location.has("feature")){
                                	w.feature = location.getString("feature");
                                }else{
                                	w.feature = "emptyFeature";
                                }
                                
                               
                                PhotoDisplayActivity.MyWikipedia.add(w);
 
                   
                            }
                            
                            try {
                            	PhotoDisplayActivity.fileExists(PhotoDisplayActivity.FNBWFileName);
								PhotoDisplayActivity.writeFNBWArray(PhotoDisplayActivity.FNBW, PhotoDisplayActivity.MyWikipedia);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
 
                        
 
                        } catch (JSONException e) {
                            e.printStackTrace();
      
                        }
 
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    	 hidepDialog();
                    }
                });
 
   
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    	}else{
    		
    	}
    	
    }
    
    
    
    private void showpDialog() {
        if (!PhotoDisplayActivity.pDialog.isShowing())
        	PhotoDisplayActivity.pDialog.show();
    }
 
    private void hidepDialog() {
        if (PhotoDisplayActivity.pDialog.isShowing())
        	PhotoDisplayActivity.pDialog.dismiss();
    }
 
}
