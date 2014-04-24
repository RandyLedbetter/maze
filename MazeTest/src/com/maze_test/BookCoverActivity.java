package com.maze_test;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;

public class BookCoverActivity extends Activity{
	
	//Intent intent = new Intent(this, DashboardActivity.class);
    private SharedPreferences sharedPref;
    
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookcover);
		sharedPref = getBaseContext().getSharedPreferences("path", Context.MODE_PRIVATE);
		
		Thread splashThread = new Thread() {

			@Override
	        public void run() {
				try {
		            int waited = 0;
		            while (waited < 5000) {
		            	sleep(100);
		                waited += 100;
		            }
	            } catch (InterruptedException e) {
	               // do nothing
	            } finally {
	            	finish();
	                Intent i = new Intent();
	                if(sharedPref.contains("path"))
	        	    {
	                	i.setClassName("com.maze_test",
	                			"com.maze_test.MainActivity");
	        	    }
	                else
	                {
	                	i.setClassName("com.maze_test",
	                			"com.maze_test.DashboardActivity");
	                }
	                startActivity(i);
	            }
	        }
		};
	    splashThread.start();
   }
}