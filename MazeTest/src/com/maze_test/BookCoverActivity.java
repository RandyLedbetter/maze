package com.maze_test;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class BookCoverActivity extends Activity{
	
	//Intent intent = new Intent(this, DashboardActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookcover);
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
	                i.setClassName("com.maze_test",
	                               "com.maze_test.DashboardActivity");
	                startActivity(i);
	            }
	        }
		};
	    splashThread.start();
   }
}