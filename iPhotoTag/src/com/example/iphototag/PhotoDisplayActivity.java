/**
 * 
 */
package com.example.iphototag;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import android.os.Environment;
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
    
    
    public static String FNBFileName; 
	public static String FNBPFileName; 
	public static String FNBWFileName;
	

	
	public static String FNB;
	public static String FNBP;
	public static String FNBW;

    
	
	public static ArrayList<FindNearbyContainer> MyFindNearbyArray ;
	public static ArrayList<PointOfInterest> MyPointsOfInterest ;
	public static ArrayList<WikipediaContainer> MyWikipedia;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_photo);

		arealButton =(Button)findViewById(R.id.arealButton);
	//	bestFitButton = (Button) findViewById(R.id.bestFitButton);
		
		

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
		
		
		position = getIntent().getExtras().getInt("imgPos");
    
		
		
		
		ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
		imageView.setImageBitmap(GalleryActivity.TaggedPhotosContainer.get(position).image);
		imageView.layout(10,10, 10, 10);
		
		
		arealButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v ) {
	
				openAreal(v);
				
			}
		});
		
		double latitude = 0;
		double longitude = 0;
		
		ImageObject o = GalleryActivity.TaggedPhotosContainer.get(position);
		
		
		latitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, IPhotoTagDatabaseHandler.fieldLatitude));
		longitude = Double.parseDouble(MainActivity.db.getOneFieldData(o.imageAbsolutePath, IPhotoTagDatabaseHandler.fieldLongitude));


		MyFindNearbyArray = new ArrayList<FindNearbyContainer>();
		MyWikipedia = new ArrayList<WikipediaContainer>();
		MyPointsOfInterest = new ArrayList<PointOfInterest>();
		
		
		GeonamesFunctions gn = new GeonamesFunctions();
	
		
		String imageFileName = MainActivity.db.getOneFieldData(o.imageAbsolutePath, IPhotoTagDatabaseHandler.fieldPhotoName);
	    
		FNBFileName = "FNB" + imageFileName.replace(".jpg", ".txt");
		FNBPFileName = "FNBP" + imageFileName.replace(".jpg", ".txt");
		FNBWFileName = "FNBW" + imageFileName.replace(".jpg", ".txt");
		
		File sdCard = Environment.getExternalStorageDirectory();
    	FNB = sdCard.getAbsolutePath() + "/iPhotoTagsArrays/"+FNBFileName;
    	FNBP = sdCard.getAbsolutePath() + "/iPhotoTagsArrays/"+FNBPFileName;
    	FNBW = sdCard.getAbsolutePath() + "/iPhotoTagsArrays/"+FNBWFileName;
    	
    	File f = new File(FNB);
    	if(!f.exists()){
    		gn.findNearBy(latitude, longitude);
    	}else{
    		 try {
				MyFindNearbyArray = loadFNBArray(FNB);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	File f2 = new File(FNBP);
    	if(!f2.exists()){
    	gn.findNearbyPointsOfInterest(latitude, longitude);
    	}else{
    		try {
				MyPointsOfInterest = loadFNBPArray(FNBP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	File f3 = new File(FNBW);
    	if(!f3.exists()){
    		gn.findNearWikipedia(latitude, longitude);
    	}else{
    		try {
				MyWikipedia = loadFNBWArray(FNBW);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    
	/*	try {
			if(fileExists(FNBFileName)==0){
				try {
				  
					
				 MyFindNearbyArray = loadFNBArray(FNB);
				 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				gn.findNearBy(latitude, longitude);
				
				try {
					writeFNBArray(FNB , MyFindNearbyArray);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			if(fileExists(FNBPFileName)==0){
				try {
					
					
				MyPointsOfInterest = loadFNBPArray(FNBP);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				gn.findNearbyPointsOfInterest(latitude, longitude);
				try {
					writeFNBPArray(FNBP , MyPointsOfInterest);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			if(fileExists(FNBWFileName)==0){
				try {
					
					
				MyWikipedia  = loadFNBWArray(FNBW);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				gn.findNearWikipedia(latitude, longitude);
				try {
					
					writeFNBWArray(FNBW , MyWikipedia);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	*/
		
	}   
	
	
	
    public void openAreal(View view) {
   
        Intent intent = new Intent(this, ArealActivity.class);
        
       
        startActivity(intent);
    }


    
    public static int fileExists(String filePathString) throws IOException{
    	File sdCard = Environment.getExternalStorageDirectory();
    	File f = new File (sdCard.getAbsolutePath() + "/iPhotoTagsArrays/"+filePathString);

    	int result = 0;
    	
    	if(!f.exists()) { 
    		File n = new File (sdCard.getAbsolutePath() + "/iPhotoTagsArrays " ,filePathString);
    		result =1;
    	
    	}
    	
    	return result;
    }
    
    public static void writeFNBArray(String fileName, ArrayList<FindNearbyContainer> FN) throws IOException{
    	
		 //	File file = new File(fileName);
	    //	FileWriter fw = new FileWriter(file);
	    	BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(fileName)));
	    	
	    	for(FindNearbyContainer f : FN){
	    
	    		bfw.write(f.toponymName);
	    		bfw.newLine();
	    		bfw.write(f.name);
	    		bfw.newLine();
	    		bfw.write(f.distance);
	    		bfw.newLine();
	    		bfw.write(f.fcl);
	    		bfw.newLine();
	    		bfw.write(f.fcode);
	    		bfw.newLine();
	    		bfw.write(f.lat);
	    		bfw.newLine();
	    		bfw.write(f.lng);
	    		bfw.newLine();
	    		bfw.write(f.geonameId);
	    		bfw.newLine();
	    		bfw.write("---");
	    		bfw.newLine();
	    	
	    		
	    	}
	    	//bfw.flush();
	    	bfw.close();
	    	
	    }
	    
	    public static ArrayList<FindNearbyContainer> loadFNBArray(String fileName) throws IOException{
	    	
	    	File file = new File(fileName);
	    	ArrayList<FindNearbyContainer> X = new ArrayList<FindNearbyContainer>();
	    	BufferedReader bfr = new BufferedReader(new FileReader(file));
	    	String line = "";
	    	while((line = bfr.readLine())!= null){
	    		
	    		FindNearbyContainer P = new FindNearbyContainer();
	    		P.toponymName = line;
	    		P.name = (line = bfr.readLine());
	    		P.distance = (line = bfr.readLine());
	    		P.fcl = (line = bfr.readLine());
	    		P.fcode =(line = bfr.readLine());
	    		P.lat = (line = bfr.readLine());
	    		P.lng = (line = bfr.readLine());
	    		P.geonameId = (line = bfr.readLine());
	    		
	    		X.add(P);
	    		
	    		line = bfr.readLine();
	    		
	    	}
	    	bfr.close();
	    	
	    	return X;
	    }
    	
    	
	    public static void writeFNBPArray(String fileName, ArrayList<PointOfInterest> FN) throws IOException{
	    	
			 //	File file = new File(fileName);
		    //	FileWriter fw = new FileWriter(file);
		    	BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(fileName)));
		    	
		    	for(PointOfInterest f : FN){
		    
		    		bfw.write(f.typeName);
		    		bfw.newLine();
		    		bfw.write(f.name);
		    		bfw.newLine();
		    		bfw.write(f.distance);
		    		bfw.newLine();
		    		bfw.write(f.lat);
		    		bfw.newLine();
		    		bfw.write(f.lng);
		    		bfw.newLine();
		    		bfw.write("---");
		    		bfw.newLine();
		    	
		    		
		    	}
		    	//bfw.flush();
		    	bfw.close();
		    	
		    }
		    
		    public static ArrayList<PointOfInterest> loadFNBPArray(String fileName) throws IOException{
		    	
		    	File file = new File(fileName);
		    	ArrayList<PointOfInterest> X = new ArrayList<PointOfInterest>();
		    	BufferedReader bfr = new BufferedReader(new FileReader(file));
		    	String line = "";
		    	while((line = bfr.readLine())!= null){
		    		
		    		PointOfInterest P = new PointOfInterest();
		    		P.typeName = line;
		    		P.name = (line = bfr.readLine());
		    		P.distance = (line = bfr.readLine());
		    		P.lat = (line = bfr.readLine());
		    		P.lng = (line = bfr.readLine());
		    		
		    		
		    		X.add(P);
		    		
		    		line = bfr.readLine();
		    		
		    	}
		    	bfr.close();
		    	
		    	return X;
		    }
   
		    public static void writeFNBWArray(String fileName, ArrayList<WikipediaContainer> FN) throws IOException{
		    	
				 //	File file = new File(fileName);
			    //	FileWriter fw = new FileWriter(file);
			    	BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(fileName)));
			    	
			    	for(WikipediaContainer f : FN){
			    
			    		bfw.write(f.title);
			    		bfw.newLine();
			    		bfw.write(f.summary);
			    		bfw.newLine();
			    		bfw.write(f.distance);
			    		bfw.newLine();
			    		bfw.write(f.feature);
			    		bfw.newLine();
			    		bfw.write(f.rank);
			    		bfw.newLine();
			    		bfw.write(f.lat);
			    		bfw.newLine();
			    		bfw.write(f.lng);
			    		bfw.newLine();
			    		bfw.write(f.wikipediaUrl);
			    		bfw.newLine();
			    		bfw.write("---");
			    		bfw.newLine();
			    	
			    		
			    	}
			    	//bfw.flush();
			    	bfw.close();
			    	
			    }
			    
			    public static ArrayList<WikipediaContainer> loadFNBWArray(String fileName) throws IOException{
			    	
			    	File file = new File(fileName);
			    	ArrayList<WikipediaContainer> X = new ArrayList<WikipediaContainer>();
			    	BufferedReader bfr = new BufferedReader(new FileReader(file));
			    	String line = "";
			    	while((line = bfr.readLine())!= null){
			    		
			    		WikipediaContainer P = new WikipediaContainer();
			    		P.title = line;
			    		P.summary = (line = bfr.readLine());
			    		P.distance = (line = bfr.readLine());
			    		P.feature = (line = bfr.readLine());
			    		P.rank =(line = bfr.readLine());
			    		P.lat = (line = bfr.readLine());
			    		P.lng = (line = bfr.readLine());
			    		P.wikipediaUrl = (line = bfr.readLine());
			    		
			    		X.add(P);
			    		
			    		line = bfr.readLine();
			    		
			    	}
			    	bfr.close();
			    	
			    	return X;
			    }
    
}
