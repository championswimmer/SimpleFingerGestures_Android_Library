package in.championswimmer.sfg.lib;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by championswimmer on 12/4/14.
 */
public class SimpleFingerGestures implements View.OnTouchListener {

    public interface On1FingerGestureListener {

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





    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
