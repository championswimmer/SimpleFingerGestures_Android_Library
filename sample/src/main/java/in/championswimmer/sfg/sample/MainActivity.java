package in.championswimmer.sfg.sample;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.championswimmer.sfg.lib.SimpleFingerGestures;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

int smallestWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mv = (ImageView) findViewById(R.id.myview);
        final TextView grtv = (TextView) findViewById(R.id.gestureResultTextView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (width > height) {
            smallestWidth = height;
        } else {
            smallestWidth = width;
        }

        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setDebug(true);
        sfg.setConsumeTouchEvents(true);

        sfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            double distancePercentage = 0.0;
            @Override
            public boolean onSwipeUp(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  up " + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeDown(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  down " + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeLeft(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  left " + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeRight(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You swiped " + fingers + " fingers  right " + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onPinch(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You pinched " + fingers + " fingers " + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onUnpinch(int fingers, long gestureDuration, double gestureDistance) {
                this.distancePercentage = gestureDistance/smallestWidth;
                Toast.makeText(MainActivity.this, "You unpinched " + fingers + "fingers"  + gestureDuration + " milliseconds " + distancePercentage + " far", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onDoubleTap(int fingers) {
                Toast.makeText(MainActivity.this, "You double tapped", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        mv.setOnTouchListener(sfg);

    }

}
