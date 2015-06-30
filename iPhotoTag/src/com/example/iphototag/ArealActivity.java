package com.example.iphototag;

//Import Libraries
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

//Create main class
public class ArealActivity extends FragmentActivity{

  //Declare ViewPager and ActionBar variables
  ViewPager viewPager;
  ActionBar actionBar;
  public static FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_areal);

      fragmentManager = getSupportFragmentManager();
      //Initialise ViewPager
      viewPager = (ViewPager) findViewById(R.id.pager);
      viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

     
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();
      if (id == R.id.action_settings) {
          return true;
      }
      return super.onOptionsItemSelected(item);
  }


}

class MyAdapter extends FragmentPagerAdapter{

  public MyAdapter(FragmentManager fm) {
      super(fm);
      // TODO Auto-generated constructor stub
  }

  @Override
  public Fragment getItem(int position) {
      switch (position) {
          case 0:
              return new PhotoDataFragment();
          case 1:
              return new MapFragment();
          case 2:
              return new PointsOfInterestFragment();
      }
      return null;
  }

  @Override
  public int getCount() {
      // TODO Auto-generated method stub
      return 3;
  }

}