package com.example.iphototag;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class ImageObject {
	
	public int position;
	public String imageAbsolutePath;
	public String imageTitle;
	public Bitmap image;
	
	public ArrayList<TagData> tags = new ArrayList<TagData>();
	
	

}
