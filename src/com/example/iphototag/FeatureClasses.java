package com.example.iphototag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Environment;

public class FeatureClasses {

	public static ArrayList<FeatureCode> MyFeatureCodes = new ArrayList<FeatureCode>();
	
	public void loadClasses(){
		
		
		
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,"/iPhotoTagData/MyFeatureCodes.txt");

		StringBuilder text = new StringBuilder();
		FeatureCode f;
		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    String [] result;
		    while ((line = br.readLine()) != null){
		      
		    	result = line.split("(\\.)|(\t)");
		
		    	if(result[2].contains("(s")){
		    		result[2] = result[2].replace("(s)", "");
		    	}
		    	f = new FeatureCode(result[0],result[1],result[2]);
		    	MyFeatureCodes.add(f);
		    	
		    }
		    br.close();
		}
		catch (IOException e) {
		   
		}

		
	}
}
