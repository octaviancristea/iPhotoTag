package com.example.iphototag;

import java.util.ArrayList;
import java.util.List;




 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class IPhotoTagDatabaseHandler extends SQLiteOpenHelper{

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "iPhotoTagDatabase2";
	
	public static String fieldTableName = "PhotoTags";
	
	public static String fieldPhotoName = "photoName";
	public static String fieldAbsolutePath = "absolutePath";
	public static String fieldLatitude = "Latitude";
	public static String fieldLongitude = "Longitude";
	public static String fieldFNBArray = "FNBArray";
	public static String fieldFNBPArray = "FNBPArray";
	public static String fieldFNBWArray = "FNBWArray";
	
	
	
	public IPhotoTagDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String iPhotoTagDatabaseCreate = "CREATE TABLE PhotoTags(absolutePath TEXT PRIMARY KEY,photoName TEXT, Latitude TEXT, Longitude TEXT, FNBArray TEXT, FNBPArray TEXT,FNBWArray TEXT)";
		db.execSQL(iPhotoTagDatabaseCreate);
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		 db.execSQL("DROP TABLE IF EXISTS PhotoTags");
	     onCreate(db);
		
	}
	
	public void addTaggedPhoto(String absolutePath){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(fieldAbsolutePath,absolutePath);
		values.put(fieldPhotoName, "empty");
		values.put(fieldLatitude,"empty");
		values.put(fieldLongitude,"empty");
		values.put(fieldFNBArray,"empty");
		values.put(fieldFNBPArray,"empty");
		values.put(fieldFNBWArray,"empty");
		
		
		
		db.insert(IPhotoTagDatabaseHandler.fieldTableName, null, values);
		db.close();
		
	}
	
	public void updateTagData(String absolutePath, String whatToUpdate, String data){
		
		
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues contentValues = new ContentValues();
	    
	    contentValues.put(whatToUpdate, data);
	 
	
	    db.update(IPhotoTagDatabaseHandler.fieldTableName, contentValues, IPhotoTagDatabaseHandler.fieldAbsolutePath+ "= ?", new String[] { absolutePath } );
	  
	}
	

	
	public String getOneFieldData(String absolutePath, String whatToGet){
		String oneFieldData ="";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(IPhotoTagDatabaseHandler.fieldTableName, new String[] {whatToGet/*IPhotoTagDatabaseHandler.fieldAbsolutePath,IPhotoTagDatabaseHandler.fieldPhotoName, IPhotoTagDatabaseHandler.fieldLatitude, IPhotoTagDatabaseHandler.fieldLongitude, IPhotoTagDatabaseHandler.fieldFNBArray , IPhotoTagDatabaseHandler.fieldFNBPArray , IPhotoTagDatabaseHandler.fieldFNBWArray */}, IPhotoTagDatabaseHandler.fieldAbsolutePath + "=?",
	                new String[] { absolutePath }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
		
	   int i = 0;

	  /* if(whatToGet.equals(fieldAbsolutePath)){
		   i = 0;
	   }
	   
	   if(whatToGet.equals(fieldPhotoName)){
		   i = 1;
	   }
	   
	   if(whatToGet.equals(fieldLongitude)){
		   i = 3;
	   }
	        
	   if(whatToGet.equals(fieldLatitude)){
		   i = 2;
	   }
	   if(whatToGet.equals(fieldFNBArray)){
		   i = 4;
	   }
	   
	   if(whatToGet.equals(fieldFNBPArray)){
		   i = 5;
	   }
	   
	   if(whatToGet.equals(fieldFNBWArray)){
		   i = 6;
	   }
	        */
		oneFieldData = cursor.getString(0);
		
		return oneFieldData;
	}
	
	
	

	

public  String parseFNBArrayToString(ArrayList<FindNearbyContainer> FN){
		
		String Data = "";
		
		for(FindNearbyContainer p : FN){
			
			if(p.toponymName.isEmpty()){
				p.toponymName ="sss";
			}
			p.toponymName = p.toponymName.replace("#", " ");
			p.toponymName = p.toponymName.replace("-", " ");
			Data += p.toponymName + "###";
			if(p.name.isEmpty()){
				p.name ="sss";
			}
			p.name = p.name.replace("#", " ");
			p.name = p.name.replace("-", " ");
			Data += p.name + "###";
			if(p.distance.isEmpty()){
				p.distance ="sss";
			}
			p.distance = p.distance.replace("#", " ");
			p.distance = p.distance.replace("-", " ");
			Data += p.distance + "###";
			if(p.lat.isEmpty()){
				p.lat ="sss";
			}
			p.lat = p.lat.replace("#", " ");
			p.lat = p.lat.replace("-", " ");
			Data += p.lat + "###";
			if(p.lng.isEmpty()){
				p.lng ="sss";
			}
			p.lng = p.lng.replace("#", " ");
			p.lng = p.lng.replace("-", " ");
			Data += p.lng+ "###";
			if(p.fcl.isEmpty()){
				p.fcl ="sss";
			}
			p.fcl = p.fcl.replace("#", " ");
			p.fcl = p.fcl.replace("-", " ");
			Data += p.fcl +"###";
			if(p.fcode.isEmpty()){
				p.fcode ="sss";
			}
			p.fcode = p.fcode.replace("#", " ");
			p.fcode = p.fcode.replace("-", " ");
			Data += p.fcode +"###";
			if(p.geonameId.isEmpty()){
				p.geonameId ="sss";
			}
			p.geonameId = p.geonameId.replace("#", " ");
			p.geonameId = p.geonameId.replace("-", " ");
			Data += p.geonameId+"###";
			Data += "---";
			
		}
		
		return Data;
	}
	

public ArrayList<FindNearbyContainer> parseStringToFNBArray(String Data){
	
	ArrayList<FindNearbyContainer> MyArray = new ArrayList<FindNearbyContainer>();
	

	String[] result1 = Data.split("---"); // points of interest
	
	
	for(int i = 0 ; i < result1.length ; i++){
		
		String [] result2 = result1[i].split("###");
	
		FindNearbyContainer P = new FindNearbyContainer();
		
		P.toponymName = result2[0];
		P.name = result2[1];
		P.distance = result2[2];
		P.lat = result2[3];
		P.lng = result2[4];
		P.fcl = result2[5];
		P.fcode = result2[6];
		P.geonameId = result2[7];
	
		MyArray.add(P);

	}

	return MyArray;
	
}
	
	///////////////////////////////////////////////////////////////////////////////

public   String parseFNBPArrayToString(ArrayList<PointOfInterest> FN){
	
	String Data = "";
	
	for(PointOfInterest p : FN){
		
		if(p.typeName.isEmpty()){
			p.typeName ="sss";
		}
		p.typeName = p.typeName.replace("#", " ");
		p.typeName = p.typeName.replace("-", " ");
		Data += p.typeName + "###";
		if(p.name.isEmpty()){
			p.name ="sss";
		}
		p.name = p.name.replace("#", " ");
		p.name = p.name.replace("-", " ");
		Data += p.name + "###";
		if(p.distance.isEmpty()){
			p.distance ="sss";
		}
		p.distance = p.distance.replace("#", " ");
		p.distance = p.distance.replace("-", " ");
		Data += p.distance + "###";
		if(p.lat.isEmpty()){
			p.lat ="sss";
		}
		p.lat = p.lat.replace("#", " ");
		p.lat = p.lat.replace("-", " ");
		
		Data += p.lat + "###";
		if(p.lng.isEmpty()){
			p.lng ="sss";
		}
		p.lng = p.lng.replace("#", " ");
		p.lng = p.lng.replace("-", " ");
		Data += p.lng+"###";
		Data += "---";
		
		
	}
	
	return Data;
}


public  ArrayList<PointOfInterest> parseStringToFNBPArray(String Data){

ArrayList<PointOfInterest> MyArray = new ArrayList<PointOfInterest>();

String[] result1 = Data.split("---"); // points of interest


for(int i = 0 ; i < result1.length ; i++){

	String [] result2 = result1[i].split("###");

	PointOfInterest P = new PointOfInterest();
	P.typeName = result2[0];
	P.name = result2[1];
	P.distance = result2[2];
	P.lat = result2[3];
	P.lng = result2[4];
	
	MyArray.add(P);

}

return MyArray;

}

	/////////////////////////////////////////////////////////////////////////////

public  String parseFNBWArrayToString(ArrayList<WikipediaContainer> FN){
	
	String Data = "";
	
	for(WikipediaContainer p : FN){
		
		if(p.title.isEmpty()){
			p.title ="sss";
		}
		p.title = p.title.replace("#", " ");
		p.title = p.title.replace("-", " ");
		
		Data += p.title + "###";
		
		
		
		if(p.summary.isEmpty()){
			p.summary ="sss";
		}
		p.summary = p.summary.replace("#", " ");
		p.summary = p.summary.replace("-", " ");
		
		Data += p.summary + "###";
		if(p.distance.isEmpty()){
			p.distance ="sss";
		}
		p.distance = p.distance.replace("#", " ");
		p.distance = p.distance.replace("-", " ");
		
		Data += p.distance + "###";
		if(p.lat.isEmpty()){
			p.lat ="sss";
		}
		p.lat = p.lat.replace("#", " ");
		p.lat = p.lat.replace("-", " ");
		Data += p.lat + "###";
		if(p.lng.isEmpty()){
			p.lng ="sss";
		}
		p.lng = p.lng.replace("#", " ");
		p.lng = p.lng.replace("-", " ");
		Data += p.lng+ "###";
		if(p.rank.isEmpty()){
			p.rank ="sss";
		}
		p.rank = p.rank.replace("#", " ");
		p.rank = p.rank.replace("-", " ");
		Data += p.rank+ "###";
		if(p.feature.isEmpty()){
			p.feature ="sss";
		}
		p.feature = p.feature.replace("#", " ");
		p.feature = p.feature.replace("-", " ");
		Data += p.feature+ "###";
		if(p.wikipediaUrl.isEmpty()){
			p.wikipediaUrl ="sss";
		}
		p.wikipediaUrl = p.wikipediaUrl.replace("#", " ");
		p.wikipediaUrl = p.wikipediaUrl.replace("-", " ");
		Data += p.wikipediaUrl+"###";
		
		Data += "---";
		
		
	}
	
	return Data;
}


public ArrayList<WikipediaContainer> parseStringToFNBWArray(String Data){

ArrayList<WikipediaContainer> MyArray = new ArrayList<WikipediaContainer>();

String[] result1 = Data.split("---"); // points of interest


for(int i = 0 ; i < result1.length ; i++){

	String [] result2 = result1[i].split("###");
	
	WikipediaContainer P = new WikipediaContainer();
	P.title = result2[0]; 
	P.summary = result2[1];
	P.distance = result2[2];
	P.lat = result2[3];
	P.lng = result2[4];
	P.rank = result2[5];
	P.feature = result2[6];
	P.wikipediaUrl = result2[7];
	
	MyArray.add(P);

}

return MyArray;

}

	

}
