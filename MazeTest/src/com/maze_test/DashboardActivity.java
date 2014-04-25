package com.maze_test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class DashboardActivity extends Activity
implements View.OnTouchListener {
	
	Button enterMazeButton;
	Button resumeButton;
	Button startOverButton;
	Button optionsButton;
	Button creditsButton;
	Button directionsButton;
	private SharedPreferences sharedPref;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		sharedPref = getBaseContext().getSharedPreferences("path", Context.MODE_PRIVATE);
		
		ImageView iv = (ImageView) findViewById (R.id.image);
	    iv.setImageResource(R.drawable.dashboard);
	    iv.setTag(R.drawable.dashboard);
	    if (iv != null) {
	       iv.setOnTouchListener (this);
	    }
	    
	    if(sharedPref.contains("path"))
	    {
	    	resumeButton = (Button) findViewById(R.id.resume_button);
	    	resumeButton.setVisibility(View.VISIBLE);
	    }
	    
	    addListenerOnButton();
	    
	}

	public boolean onTouch (View v, MotionEvent ev) 
	{
	    boolean state = false;

	    final int action = ev.getAction();

	    final int evX = (int) ev.getX();
	    final int evY = (int) ev.getY();

	    ImageView imageView = (ImageView) v.findViewById(R.id.image);
	    ImageView imageMap = (ImageView) findViewById(R.id.image_areas);
	    if (imageView == null) return false;


	    switch (action) {
	    case MotionEvent.ACTION_DOWN :


	    case MotionEvent.ACTION_UP :
	       int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
	       ColorTool ct = new ColorTool ();
	       int tolerance = 25;
	      
	       if (ct.closeMatch (Color.RED, touchColor, tolerance)) {
	    	// Set imageView to room 1
		   		//imageView.setImageResource(R.drawable.room1);
		   		//imageView.setTag(R.drawable.room1);
	   		// Set imageMap to image map 1
		   		//imageMap.setImageResource(R.drawable.image_map1);
		   		//imageMap.setTag(R.drawable.image_map1); 
	    	   state=true; 
	    	   Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
	    	   startActivity(intent);	    	   
	       }

	    default:
	       state = false;
	    } // end switch
	 
	   return state;
	}
	
	public void addListenerOnButton() {
		
		
		// Enter Maze Button Events. Since the Start Over Button will occupy
		// the same space, we will use this enterMazeButton listener to detect
		// the state and change the android:background property to start_over_button
		// to switch the display to that button. Both buttons will do the same
		// thing: lead to room 1.
		enterMazeButton = (Button) findViewById(R.id.enter_maze_button);
 
		enterMazeButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				SharedPreferences.Editor prefEditor = sharedPref.edit();
				prefEditor.clear();
				//starting a new game, fill path shared pref with empty vector
				prefEditor.remove("path");
				prefEditor.commit();
				Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
				startActivity(intent);
 
			}
 
		});
		
		// Resume Button Events 
		resumeButton = (Button) findViewById(R.id.resume_button);
 
		resumeButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
 
			}
 
		});
		
		// Options Button Events 
		optionsButton = (Button) findViewById(R.id.options_button);
 
		optionsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				ArrayList selectedItems;
				selectedItems = new ArrayList();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
		 
				// set title
				alertDialogBuilder.setTitle("Your Title");
				
				// set dialog message
				alertDialogBuilder.setTitle(R.string.options_menu).setMultiChoiceItems(R.array.options, null, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		                /*if (isChecked) {
		                    // If the user checked the item, add it to the selected items
		                    selectedItems.add(which);
		                } else if (selectedItems.contains(which)) {
		                    // Else, if the item is already in the array, remove it 
		                    selectedItems.remove(Integer.valueOf(which));
		                }*/
		            }
				})    // Set the action buttons
		        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int id) {
		                // User clicked OK, so save the mSelectedItems results somewhere
		                // or return them to the component that opened the dialog
		            }
		        })
		        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int id) {
		            }
		        });
	 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
 
			}
 
		});
		
		// Credits Button Events 
		creditsButton = (Button) findViewById(R.id.credits_button);
 
		creditsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DashboardActivity.this, CreditsActivity.class);
				startActivity(intent);
 
			}
 
		});
		
		// Directions Button Events 
		directionsButton = (Button) findViewById(R.id.directions_button);
 
		directionsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(DashboardActivity.this, DirectionsActivity.class);
				startActivity(intent);
 
			}
 
		});
 
	}
	
// ==================================================================================
// Utility Functions
// ==================================================================================
	
public int getHotspotColor (int hotspotId, int x, int y) {
    ImageView img = (ImageView) findViewById (hotspotId);
    if (img == null) {
       Log.d ("ImageAreasActivity", "Hot spot image not found");
       return 0;
    } else {
      img.setDrawingCacheEnabled(true); 
      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
      if (hotspots == null) {
         Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
         return 0;
      } else {
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
      }
    }
} // end getHotspotColor

public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
} // end toast


} // End DashboardActivity
