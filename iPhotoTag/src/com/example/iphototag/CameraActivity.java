/**
 * 
 */
package com.example.iphototag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


/**
 * @author ocristea
 *
 */
public class CameraActivity extends Activity {


	Preview preview;
	Camera camera;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		

		setContentView(R.layout.activity_camera);

		preview = new Preview(this, (SurfaceView)findViewById(R.id.surfaceView));
		preview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		((FrameLayout) findViewById(R.id.layout)).addView(preview);
		preview.setKeepScreenOn(true);


		Button captureButton = (Button) findViewById(R.id.button1);
		captureButton.setOnClickListener(
		    new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		
		            camera.takePicture(null, null, jpegCallback);
		        }
		    }
		);


	

	}

	@Override
	protected void onResume() {
		super.onResume();
		int numCams = Camera.getNumberOfCameras();
		if(numCams > 0){
			try{
				camera = Camera.open(0);
				camera.startPreview();
				preview.setCamera(camera);
			} catch (RuntimeException ex){
			
			}
		}
	}

	@Override
	protected void onPause() {
		if(camera != null) {
			camera.stopPreview();
			preview.setCamera(null);
			camera.release();
			camera = null;
		}
		super.onPause();
	}

	private void resetCam() {
		camera.startPreview();
		preview.setCamera(camera);
	}

	private void refreshGallery(File file) {
		Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		mediaScanIntent.setData(Uri.fromFile(file));
		sendBroadcast(mediaScanIntent);
	}


	PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			new SaveImageTask().execute(data);
			resetCam();
		
		}
	};

	private class SaveImageTask extends AsyncTask<byte[], Void, Void> {

		@Override
		protected Void doInBackground(byte[]... data) {
			FileOutputStream outStream = null;
			long photoName = 0;
			// Write to SD Card
			try {
				GalleryActivity.verifyCreatePhotoGalleryFolder();
				
				File sdCard = Environment.getExternalStorageDirectory();
				File dir = new File (sdCard.getAbsolutePath() + "/iPhotoTags");
				dir.mkdirs();				

				photoName = System.currentTimeMillis();
				
				String fileName = String.format("%d.jpg", photoName);
				//String photoData = String.format("%d.txt",photoName);

				
				File outFile = new File(dir, fileName);
				
				
				
				//File outDataFile = new File(dir, photoData);
		
				//FileWriter writer = new FileWriter(outDataFile);
				//double longitude = MainActivity.Longitude;
				//double latitude = MainActivity.Latitude;
				
				//writer.append("File Name :"+fileName+"\n");
				//writer.append("Longitude :"+longitude+"\n");
				//writer.append("Latitude  :"+latitude+"\n");
				//writer.flush();
				//writer.close();
				
				String lat = ""+MainActivity.Latitude;
				String lng = ""+ MainActivity.Longitude;
			
				MainActivity.db.addTaggedPhoto(outFile.getAbsolutePath());
				MainActivity.db.updateTagData(outFile.getAbsolutePath(),MainActivity.db.fieldPhotoName , fileName);
				MainActivity.db.updateTagData(outFile.getAbsolutePath(), MainActivity.db.fieldLatitude ,  lat);
				MainActivity.db.updateTagData(outFile.getAbsolutePath(), MainActivity.db.fieldLongitude , lng);
				
				
				outStream = new FileOutputStream(outFile);
				outStream.write(data[0]);
				outStream.flush();
				outStream.close();

				
				refreshGallery(outFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			return null;
		}

	}
	
 
}


