package com.maze_test;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This activity displays an image on the screen. 
 * The image has three different regions that can be clicked / touched.
 * When a region is touched, the activity changes the view to show a different
 * image.
 *
 */

public class MainActivity extends Activity 
    implements View.OnTouchListener 
{

/**
 * Create the view for the activity.
 *
 */

@Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = (ImageView) findViewById (R.id.image);
    iv.setImageResource(R.drawable.room1);
    iv.setTag(R.drawable.room1);
    if (iv != null) {
       iv.setOnTouchListener (this);
    }
    
    //ImageView im = (ImageView) findViewById(R.id.image_areas);
    //im.setImageResource(R.drawable.image_map1);
    //im.setTag(R.drawable.image_map1);
    
    toast ("Touch the screen to discover where the regions are.");
}

/**
 * Respond to the user touching the screen.
 * Change images to make things appear and disappear from the screen.
 *
 */    

public boolean onTouch (View v, MotionEvent ev) 
{
    boolean state = false;

    final int action = ev.getAction();

    final int evX = (int) ev.getX();
    final int evY = (int) ev.getY();
    //int nextImage = -1;			// resource id of the next image to display

    // If we cannot find the imageView, return.
    ImageView imageView = (ImageView) v.findViewById(R.id.image);
    ImageView imageMap = (ImageView) findViewById(R.id.image_areas);
    if (imageView == null) return false;

    // When the action is Down, see if we should show the "pressed" image for the default image.
    // We do this when the default image is showing. That condition is detectable by looking at the
    // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
    //Integer tagNum = (Integer) imageView.getTag ();
    //int currentResource = (tagNum == null) ? R.drawable.room1 : tagNum.intValue ();

    // Now that we know the current resource being displayed we can handle the DOWN and UP events.

    switch (action) {
    case MotionEvent.ACTION_DOWN :
       //if (currentResource == R.drawable.room1) {
         // nextImage = R.drawable.room20;
         // handledHere = true;
       /*
       } else if (currentResource != R.drawable.p2_ship_default) {
         nextImage = R.drawable.p2_ship_default;
         handledHere = true;
       */
      // } else handledHere = true;
       //break;

    case MotionEvent.ACTION_UP :
       // On the UP, we do the click action.
       // The hidden image (image_areas) has three different hotspots on it.
       // The colors are red, blue, and yellow.
       // Use image_areas to determine which region the user touched.
       int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
      
       // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
       // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
       // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
       // varying pixel density.
       ColorTool ct = new ColorTool ();
       int tolerance = 25;
      
       if (ct.closeMatch (Color.RED, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "RED"); state=true; toast("RED true");}
       else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "BLUE"); state = true; toast("BLUE true");}
       else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "YELLOW"); state = true; toast("YELLOW true");}
       else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "WHITE"); state = true; toast("WHITE true");}
  

    default:
       state = false;
    } // end switch
 
   return state;
}
   

/**
 */
// More methods

private void getNextRoom(ImageView view, ImageView map, String color) {
	//now we set our rooms
	// <R.drawable.room#, map<color, pair<associatedRoom#. associatedRoomMap#>>>
	SparseArray<Map<String, Pair<Integer, Integer>>> rooms = new SparseArray<Map<String, Pair<Integer, Integer>>>();
	

	//room 1
	Map<String, Pair<Integer, Integer>> room1 = new HashMap<String, Pair<Integer, Integer>>();
	room1.put("BLUE", new Pair<Integer, Integer>(R.drawable.room20, R.drawable.image_map20));
	room1.put("RED", new Pair<Integer, Integer>(R.drawable.room26, R.drawable.image_map26));
	room1.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room41, R.drawable.image_map41));
	room1.put("FUSCIA", new Pair<Integer, Integer>(R.drawable.room21, R.drawable.image_map21));
	
	//room 2
	Map<String, Pair<Integer, Integer>> room2 = new HashMap<String, Pair<Integer, Integer>>();
	room2.put("BLUE", new Pair<Integer, Integer>(R.drawable.room29, R.drawable.image_map29));
	room2.put("RED", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room2.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room12, R.drawable.image_map12));

	//room 3
	Map<String, Pair<Integer, Integer>> room3 = new HashMap<String, Pair<Integer, Integer>>();
	room3.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room33, R.drawable.image_map33));
	room3.put("RED", new Pair<Integer, Integer>(R.drawable.room9, R.drawable.image_map9));
	room3.put("BLUE", new Pair<Integer, Integer>(R.drawable.room18, R.drawable.image_map18));
	
	//room 4
	Map<String, Pair<Integer, Integer>> room4 = new HashMap<String, Pair<Integer, Integer>>();
	room4.put("BLUE", new Pair<Integer, Integer>(R.drawable.room44, R.drawable.image_map44));
	room4.put("RED", new Pair<Integer, Integer>(R.drawable.room29, R.drawable.image_map29));
	room4.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room15, R.drawable.image_map15));
	room4.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	room4.put("FUSCIA", new Pair<Integer, Integer>(R.drawable.room16, R.drawable.image_map16));
	room4.put("CYAN", new Pair<Integer, Integer>(R.drawable.room24, R.drawable.image_map24));
	room4.put("GREEN", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));


	//room 20
	Map<String, Pair<Integer, Integer>> room20 = new HashMap<String, Pair<Integer, Integer>>();
	room20.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room1, R.drawable.image_map1));
	//room20.put("BLUE", new Pair<Integer, Integer>(R.drawable.room27, R.drawable.image_map27));
	//room20.put("RED", new Pair<Integer, Integer>(R.drawable.room5, R.drawable.image_map5));
	
	//now add our room relations to the main room map!
	rooms.put(R.drawable.room1, room1);
	rooms.put(R.drawable.room2, room2);
	rooms.put(R.drawable.room3, room3);
	rooms.put(R.drawable.room4, room4);
	rooms.put(R.drawable.room20, room20);
	
	//ImageView cr = (ImageView) view;
	//ImageView cm = (ImageView) map;
	Integer curRoom = (Integer) view.getTag();
	//Integer curMap = (Integer) cm.getTag();
	curRoom = (curRoom == null) ? 0 : curRoom;
	//curMap = (cm == null) ? 0 : curMap;
	
	//error check
	//check if our room exists, and that there's something mapped to that color
	if(rooms.get(curRoom) != null && rooms.get(curRoom).get(color) != null)
	{
		//first is room image
		view.setImageResource(rooms.get(curRoom).get(color).first);
		view.setTag(rooms.get(curRoom).get(color).first);
		//second is image map
		map.setImageResource(rooms.get(curRoom).get(color).second);
		map.setTag(rooms.get(curRoom).get(color).second);
	}
	else
	{
		toast("This room has not been implemented yet!");		
	}
}

/**
 * Get the color from the hotspot image at point x-y.
 * 
 */

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
}

/**
 * Show a string on the screen via Toast.
 * 
 * @param msg String
 * @return void
 */

public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
} // end toast

} // end class

