package in.championswimmer.sfg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.championswimmer.sfg.lib.SimpleFingerGestures;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mv = (ImageView) findViewById(R.id.myview);
        final TextView grtv = (TextView) findViewById(R.id.gestureResultTextView);


        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  up", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  down", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  left", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  right", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You pinched " + fingers + " fingers", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration) {
                Toast.makeText(MainActivity.this, "You unpinched " + fingers + "fingers", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        mv.setOnTouchListener(sfg);

    }

}
