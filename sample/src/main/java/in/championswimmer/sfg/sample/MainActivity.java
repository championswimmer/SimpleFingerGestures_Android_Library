package in.championswimmer.sfg.sample;

import android.app.Activity;
import android.os.Bundle;
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


        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration) {
                grtv.setText("You swiped " + fingers + " fingers  up");
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration) {
                grtv.setText("You swiped " + fingers + " fingers  down");
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration) {
                grtv.setText("You swiped " + fingers + " fingers  left");
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration) {
                grtv.setText("You swiped " + fingers + " fingers  right");
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration) {
                grtv.setText("You pinched " + fingers + " fingers");
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration) {
                grtv.setText("You unpinched " + fingers + "fingers");
                return false;
            }
        });


        mv.setOnTouchListener(sfg);

    }

}
