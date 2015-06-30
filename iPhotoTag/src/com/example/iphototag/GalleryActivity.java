/**
 * 
 */
package com.example.iphototag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author ocristea
 *
 */
public class GalleryActivity extends Activity {

	 public static ArrayList<ImageObject> TaggedPhotosContainer ;

	  public void fillMyGallery() throws IOException {
			
		 
			String msg = "Android : ";
			ImageObject img;
			
	    	File file = new File(android.os.Environment.getExternalStorageDirectory(),"iPhotoTags");
	   
	    
	    	if (file.isDirectory()){
	    		File[]	listFile = file.listFiles();
	            	
	        		for (int i = 0; i < listFile.length; i++){
	        			if(listFile[i].getAbsolutePath().endsWith(".jpg")){
	        				
	        				
	        				img = new ImageObject();
	        				img.position = i;
	        				img.imageAbsolutePath =listFile[i].getAbsolutePath();
	        				img.imageTitle = MainActivity.db.getOneFieldData(listFile[i].getAbsolutePath(), MainActivity.db.fieldPhotoName);//listFile[i].getAbsolutePath();
	        		
	        				Bitmap b = BitmapFactory.decodeFile(listFile[i].getAbsolutePath());
	        				img.image = rotateBitmap(listFile[i].getAbsolutePath(),b);
	        			
	        				//String dataFile = listFile[i].getAbsolutePath().replace(".jpg", ".txt");
	        				
	        				
	        				//BufferedReader bfr = new BufferedReader(new FileReader(new File(dataFile)));
	        				//String line  = null;
	        				//String [] results;
	        				//while((line = bfr.readLine()) !=  null){
	        					
	        					
	        				//	results = line.split(":");
	        					
	        				//	TagData t = new TagData();
	        				//	t.variableName = results[0];
	        				//	t.variableValue = results[1];
	        					
	        				//	img.tags.add(t);
	        			//	}
	        				
	        			//	bfr.close();
	        				
	        	
	        			
	        				
	        				
	        				TaggedPhotosContainer.add(img);
	        			
	        	
	        				Log.d(msg, listFile[i].getAbsolutePath());
	        			}
	            	

	            	}
	        	}
	  	
		}
	  
	  public static Bitmap rotateBitmap(String src, Bitmap bitmap) {
		  ExifInterface exif;  
		  try {
			  	exif = new ExifInterface( src );
			  	 int orientation = exif.getAttributeInt( ExifInterface.TAG_ORIENTATION, 1 );

		        if (orientation == 1) {
		            return bitmap;
		        }

		        Matrix matrix = new Matrix();
		        switch (orientation) {
		        case 2:
		            matrix.setScale(-1, 1);
		            break;
		        case 3:
		            matrix.setRotate(180);
		            break;
		        case 4:
		            matrix.setRotate(180);
		            matrix.postScale(-1, 1);
		            break;
		        case 5:
		            matrix.setRotate(90);
		            matrix.postScale(-1, 1);
		            break;
		        case 6:
		            matrix.setRotate(90);
		            break;
		        case 7:
		            matrix.setRotate(-90);
		            matrix.postScale(-1, 1);
		            break;
		        case 8:
		            matrix.setRotate(-90);
		            break;
		        default:
		            return bitmap;
		        }

		        try {
		            Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0,
		                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		            bitmap.recycle();
		            return oriented;
		        } catch (OutOfMemoryError e) {
		            e.printStackTrace();
		            return bitmap;
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return bitmap;
		}

	  
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        TaggedPhotosContainer = new ArrayList<ImageObject>();
	        
	        Intent intent = getIntent();
	        setContentView(R.layout.activity_display_gallery);
	        
	        verifyCreatePhotoGalleryFolder();
	        try {
				fillMyGallery();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	        verifyCreateArrayGalleryFolder();
	   
	        GridView gridview = (GridView) findViewById(R.id.gridview);
	        gridview.setAdapter(new ImgAdapter(this,TaggedPhotosContainer));
	        
	       
	        gridview.setOnItemClickListener(new OnItemClickListener() {
	            
	        	@Override
	        	public void onItemClick(AdapterView<?> parent, View view,
	                    int position, long id) {
	        		
	        		Intent intent = new Intent(GalleryActivity.this,PhotoDisplayActivity.class);
	                intent.putExtra("imgPos",position);
	              //  TagData t = new TagData();
	              //  t.variableName = "StaticPositionName";
	              //  t.variableValue = ""+position;
	                
	              //  TaggedPhotosContainer.get(position).tags.add(t);
	                
	                
	                
	                GalleryActivity.this.startActivity(intent);
	        	
	        	
	        		}
	              });
	       
	        
	    }

	 public static void verifyCreatePhotoGalleryFolder(){
		 
		  
	      File folder = new File(Environment.getExternalStorageDirectory() + File.separator+"iPhotoTags");
	        boolean success = true;
	        if (!folder.exists()) {
	        	// Toast.makeText(GalleryActivity.this, "Creating Directory", Toast.LENGTH_SHORT).show();
	            success = folder.mkdir();
	        }
	        if (success) {
	           
	        } else {
	          //  Toast.makeText(GalleryActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
		 
	        }
	 }
	 
	 public static void verifyCreateArrayGalleryFolder(){
		 
		  
	      File folder = new File(Environment.getExternalStorageDirectory() + File.separator+"iPhotoTagsArrays");
	        boolean success = true;
	        if (!folder.exists()) {
	        	// Toast.makeText(GalleryActivity.this, "Creating Directory", Toast.LENGTH_SHORT).show();
	            success = folder.mkdir();
	        }
	        if (success) {
	           
	        } else {
	          //  Toast.makeText(GalleryActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
		 
	        }
	 }
	 
	 
	
	
	
}
