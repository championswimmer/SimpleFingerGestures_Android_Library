package in.championswimmer.sfg.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import in.championswimmer.sfg.lib.SimpleFingerGestures;

import in.championswimmer.sfg.sample.R;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mv = (ImageView) findViewById(R.id.myview);

        SimpleFingerGestures sfg = new SimpleFingerGestures();
        sfg.setOn1FingerGestureListener(new SimpleFingerGestures.On1FingerGestureListener() {
            @Override
            public boolean onSwipeUp() {
                Toast.makeText(getBaseContext(), "swiped up", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeDown() {
                Toast.makeText(getBaseContext(), "swiped down", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeLeft() {
                Toast.makeText(getBaseContext(), "swiped left", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeRight() {
                Toast.makeText(getBaseContext(), "swiped right", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        sfg.setOn2FingerGestureListener(new SimpleFingerGestures.On2FingerGestureListener() {
            @Override
            public boolean onSwipeUp() {
                Toast.makeText(getBaseContext(), "swiped 2 up", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeDown() {
                Toast.makeText(getBaseContext(), "swiped 2 down", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeLeft() {
                Toast.makeText(getBaseContext(), "swiped 2 left", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onSwipeRight() {
                Toast.makeText(getBaseContext(), "swiped 2 right", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onPinch() {
                Toast.makeText(getBaseContext(), "pinch", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onUnpinch() {
                Toast.makeText(getBaseContext(), "unpinch", Toast.LENGTH_SHORT).show();
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
