package in.championswimmer.sfg.lib;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by championswimmer on 12/4/14.
 */
public class SimpleFingerGestures implements View.OnTouchListener {

    public static final String TAG = "SimpleFingerGestures";
    public static final boolean DEBUG = true;

    protected boolean tracking[] = {false, false};


    private GestureAnalyser ga;
    private On1FingerGestureListener on1FingerGestureListener;
    private On2FingerGestureListener on2FingerGestureListener;

    public SimpleFingerGestures() {
        ga = new GestureAnalyser();
    }

    public void setOn1FingerGestureListener(On1FingerGestureListener o1fgl) {
        on1FingerGestureListener = o1fgl;
    }

    public void setOn2FingerGestureListener(On2FingerGestureListener o2fgl) {
        on2FingerGestureListener = o2fgl;
    }

    @Override
    public boolean onTouch(View view, MotionEvent ev) {
        if (DEBUG) Log.d(TAG, "onTouch");
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (DEBUG) Log.d(TAG, "ACTION_DOWN");
                startTracking(0);
                ga.trackGesture(ev);
                return true;
            case MotionEvent.ACTION_UP:
                if (DEBUG) Log.d(TAG, "ACTION_UP");
                if (tracking[0]) {
                    doCallBack(ga.getGesture(ev));
                }
                stopTracking(0);
                ga.untrackGesture();
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (DEBUG) Log.d(TAG, "ACTION_POINTER_DOWN");
                startTracking(1);
                ga.trackGesture(ev);
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                if (DEBUG) Log.d(TAG, "ACTION_POINTER_UP");
                if (tracking[1]) {
                    doCallBack(ga.getGesture(ev));
                }
                stopTracking(1);
                ga.untrackGesture();
                return true;
            case MotionEvent.ACTION_CANCEL:
                if (DEBUG) Log.d(TAG, "ACTION_CANCEL");
                return true;
        }
        return false;
    }

    private void doCallBack(int gestureFlag) {
        switch (gestureFlag) {
            case GestureAnalyser.SWIPE_1_UP:
                on1FingerGestureListener.onSwipeUp();
                break;
            case GestureAnalyser.SWIPE_1_DOWN:
                on1FingerGestureListener.onSwipeDown();
                break;
            case GestureAnalyser.SWIPE_1_LEFT:
                on1FingerGestureListener.onSwipeLeft();
                break;
            case GestureAnalyser.SWIPE_1_RIGHT:
                on1FingerGestureListener.onSwipeRight();
                break;

            case GestureAnalyser.SWIPE_2_UP:
                on2FingerGestureListener.onSwipeUp();
                break;
            case GestureAnalyser.SWIPE_2_DOWN:
                on2FingerGestureListener.onSwipeDown();
                break;
            case GestureAnalyser.SWIPE_2_LEFT:
                on2FingerGestureListener.onSwipeLeft();
                break;
            case GestureAnalyser.SWIPE_2_RIGHT:
                on2FingerGestureListener.onSwipeRight();
                break;
        }
    }

    private void startTracking(int nthPointer) {
        for (int i = 0; i <= nthPointer; i++) {
            tracking[i] = true;
        }
    }

    private void stopTracking(int nthPointer) {
        for (int i = nthPointer; i < tracking.length; i++) {
            tracking[i] = false;
        }
    }


    public interface On1FingerGestureListener {
        public boolean onSwipeUp();

        public boolean onSwipeDown();

        public boolean onSwipeLeft();

        public boolean onSwipeRight();

    }

    public interface On2FingerGestureListener {

        public boolean onSwipeUp();

        public boolean onSwipeDown();

        public boolean onSwipeLeft();

        public boolean onSwipeRight();

    }
}
