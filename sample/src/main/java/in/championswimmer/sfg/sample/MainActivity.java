package in.championswimmer.sfg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import in.championswimmer.sfg.lib.SimpleFingerGestures;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mv = (ImageView) findViewById(R.id.myview);
        final TextView grtv = (TextView) findViewById(R.id.gestureResultTextView);

        SimpleFingerGestures.DEBUG = true;
        SimpleFingerGestures.CONSUME_TOUCH_EVENTS = true;


        SimpleFingerGestures sfg = new SimpleFingerGestures();

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration) {
                grtv.setText("swiped " + fingers + " up");
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration) {
                grtv.setText("swiped " + fingers + " down");
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration) {
                grtv.setText("swiped " + fingers + " left");
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration) {
                grtv.setText("swiped " + fingers + " right");
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration) {
                grtv.setText("pinch");
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration) {
                grtv.setText("unpinch");
                return false;
            }
        });


        mv.setOnTouchListener(sfg);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
