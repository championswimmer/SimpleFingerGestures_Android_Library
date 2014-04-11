package in.championswimmer.sfg.lib;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by championswimmer on 12/4/14.
 */
public class SimpleFingerGestures implements View.OnTouchListener {

    public static final String TAG = "SimpleFingerGestures";

    protected boolean trackingPrimaryTouch = false;
    protected boolean trackingSecondaryTouch = false;

    private GestureAnalyser ga;


    public interface On1FingerGestureListener {
        public boolean onSwipeUp();

    }

    public interface On2FingerGestureListener {

    }

    private On1FingerGestureListener on1FingerGestureListener;
    private On2FingerGestureListener on2FingerGestureListener;

    public void setOn1FingerGestureListener (On1FingerGestureListener o1fgl) {
        on1FingerGestureListener = o1fgl;
    }

    public void setOn2FingerGestureListener (On2FingerGestureListener o2fgl) {
        on2FingerGestureListener = o2fgl;
    }

    
    public SimpleFingerGestures () {
        ga = new GestureAnalyser();
    }



    @Override
    public boolean onTouch(View view, MotionEvent ev) {
        Log.d(TAG, "onTouch");
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                trackingPrimaryTouch=true;
                ga.trackGesture(ev, 1);
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP");
                if (trackingPrimaryTouch) {
                    doCallBack(ga.getGesture(ev, 1));
                }
                trackingPrimaryTouch=false;
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "ACTION_POINTER_DOWN");
                trackingSecondaryTouch=true;
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "ACTION_POINTER_UP");
                trackingSecondaryTouch=false;
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "ACTION_CANCEL");
                return true;
        }
        return false;
    }

    private void doCallBack (int gestureFlag) {
        switch (gestureFlag) {
            case GestureAnalyser.SWIPE_1_UP:
                on1FingerGestureListener.onSwipeUp();
                break;
        }
    }
}
