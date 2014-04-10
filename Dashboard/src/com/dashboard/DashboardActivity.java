package com.dashboard;


import com.dashboard.ColorTool;
import com.dashboard.R;
import android.widget.Button;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		ImageView iv = (ImageView) findViewById (R.id.image);
	    iv.setImageResource(R.drawable.dashboard);
	    iv.setTag(R.drawable.dashboard);
	    if (iv != null) {
	       iv.setOnTouchListener (this);
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
	    	   toast("Pressed the Door Hotspot");	    	   
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
 
				Toast.makeText(DashboardActivity.this,
					"Enter Maze Button Pressed",
					Toast.LENGTH_SHORT).show();
 
			}
 
		});
		
		// Resume Button Events 
		resumeButton = (Button) findViewById(R.id.resume_button);
 
		resumeButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Toast.makeText(DashboardActivity.this,
					"Resume Button Pressed",
					Toast.LENGTH_SHORT).show();
 
			}
 
		});
		
		// Options Button Events 
		optionsButton = (Button) findViewById(R.id.options_button);
 
		optionsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Toast.makeText(DashboardActivity.this,
					"Options Button Pressed",
					Toast.LENGTH_SHORT).show();
 
			}
 
		});
		
		// Credits Button Events 
		creditsButton = (Button) findViewById(R.id.credits_button);
 
		creditsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Toast.makeText(DashboardActivity.this,
					"Credits Button Pressed",
					Toast.LENGTH_SHORT).show();
 
			}
 
		});
		
		// Directions Button Events 
		directionsButton = (Button) findViewById(R.id.directions_button);
 
		directionsButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Toast.makeText(DashboardActivity.this,
					"Directions Button Pressed",
					Toast.LENGTH_SHORT).show();
 
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
