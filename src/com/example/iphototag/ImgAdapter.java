package com.example.iphototag;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImgAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;

    private ArrayList<ImageObject> thumbnails = new ArrayList<ImageObject>();


    public ImgAdapter(Context c, ArrayList<ImageObject> taggedPhotosContainer) {
        mContext = c;
        System.out.print("thumbs: " + taggedPhotosContainer);
        thumbnails = taggedPhotosContainer;
    }

    public int getCount() {
        return thumbnails.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView)convertView;
        if (imageView == null) {
           imageView = new ImageView(mContext);
        }
        imageView.setRotation(90);
        imageView.setImageBitmap(thumbnails.get(position).image); 
        
        imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(2, 2, 2, 2);
  
        return imageView;
  }
}