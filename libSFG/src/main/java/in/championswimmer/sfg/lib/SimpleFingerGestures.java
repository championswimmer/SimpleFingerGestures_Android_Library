package in.championswimmer.sfg.lib;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author championswimmer
 * @since 0.1 12/04/14
 * @version 0.2
 *
 */
public class SimpleFingerGestures implements View.OnTouchListener {

    private static final String TAG = "SimpleFingerGestures";
    public static final boolean DEBUG = true;

    protected boolean tracking[] = {false, false, false, false, false};


    private GestureAnalyser ga;
    private On1FingerGestureListener on1FingerGestureListener;
    private On2FingerGestureListener on2FingerGestureListener;
    private On3FingerGestureListener on3FingerGestureListener;

    // Will see if these need to be used. For now just returning duration in milliS
    public static final long GESTURE_SPEED_SLOW = 1500;
    public static final long GESTURE_SPEED_MEDIUM = 1000;
    public static final long GESTURE_SPEED_FAST = 500;


    /**
     * Constructor that creates an internal {@link in.championswimmer.sfg.lib.GestureAnalyser } object as well
     */
    public SimpleFingerGestures() {
        ga = new GestureAnalyser();
    }

    /**
     * Register a callback to be invoked when 1-finger gestures take place
     *
     * <br></br>
     * <p>
     *     For the callbacks implemented via this, check the interface {@link in.championswimmer.sfg.lib.SimpleFingerGestures.On1FingerGestureListener}
     * </p>
     * @param o1fgl The callback that will run
     */
    public void setOn1FingerGestureListener(On1FingerGestureListener o1fgl) {
        on1FingerGestureListener = o1fgl;
    }

    /**
     * Register a callback to be invoked when 2-finger gestures take place
     *
     * <br></br>
     * <p>
     *     For the callbacks implemented via this, check the interface {@link in.championswimmer.sfg.lib.SimpleFingerGestures.On2FingerGestureListener}
     * </p>
     * @param o2fgl The callback that will run
     */
    public void setOn2FingerGestureListener(On2FingerGestureListener o2fgl) {
        on2FingerGestureListener = o2fgl;
    }

    /**
     * Register a callback to be invoked when 3-finger gestures take place
     *
     * <br></br>
     * <p>
     *     For the callbacks implemented via this, check the interface {@link in.championswimmer.sfg.lib.SimpleFingerGestures.On3FingerGestureListener}
     * </p>
     * @param o3fgl The callback that will run
     */
    public void setOn3FingerGestureListener(On3FingerGestureListener o3fgl) {
        on3FingerGestureListener = o3fgl;
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
                if (DEBUG) Log.d(TAG, "ACTION_POINTER_DOWN" + " " + "num" + ev.getPointerCount());
                startTracking(ev.getPointerCount()-1);
                ga.trackGesture(ev);
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                if (DEBUG) Log.d(TAG, "ACTION_POINTER_UP" + " " + "num" + ev.getPointerCount());
                if (tracking[1]) {
                    doCallBack(ga.getGesture(ev));
                }
                stopTracking(ev.getPointerCount()-1);
                ga.untrackGesture();
                return true;
            case MotionEvent.ACTION_CANCEL:
                if (DEBUG) Log.d(TAG, "ACTION_CANCEL");
                return true;
            case MotionEvent.ACTION_MOVE:
                if (DEBUG) Log.d(TAG, "ACTION_MOVE");
                return true;
        }
        return false;
    }

    private void doCallBack(GestureAnalyser.GestureType mGt) {
        switch (mGt.getGestureFlag()) {
            case GestureAnalyser.SWIPE_1_UP:
                on1FingerGestureListener.onSwipeUp(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_1_DOWN:
                on1FingerGestureListener.onSwipeDown(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_1_LEFT:
                on1FingerGestureListener.onSwipeLeft(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_1_RIGHT:
                on1FingerGestureListener.onSwipeRight(mGt.getGestureDuration());
                break;

            case GestureAnalyser.SWIPE_2_UP:
                on2FingerGestureListener.onSwipeUp(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_2_DOWN:
                on2FingerGestureListener.onSwipeDown(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_2_LEFT:
                on2FingerGestureListener.onSwipeLeft(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_2_RIGHT:
                on2FingerGestureListener.onSwipeRight(mGt.getGestureDuration());
                break;
            case GestureAnalyser.PINCH:
                on2FingerGestureListener.onPinch(mGt.getGestureDuration());
                break;
            case GestureAnalyser.UNPINCH:
                on2FingerGestureListener.onUnpinch(mGt.getGestureDuration());
                break;

            case GestureAnalyser.SWIPE_3_UP:
                on3FingerGestureListener.onSwipeUp(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_3_DOWN:
                on3FingerGestureListener.onSwipeDown(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_3_LEFT:
                on3FingerGestureListener.onSwipeLeft(mGt.getGestureDuration());
                break;
            case GestureAnalyser.SWIPE_3_RIGHT:
                on3FingerGestureListener.onSwipeRight(mGt.getGestureDuration());
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


    /**
     * Interface definition for the callback to be invoked when 1-finger gestures are performed
     */
    public interface On1FingerGestureListener {
        /**
         * Called when user swipes <b>up</b> with one finger
         * @return
         * @param gestureDuration
         */
        public boolean onSwipeUp(long gestureDuration);

        /**
         * Called when user swipes <b>down</b> with one finger
         * @return
         * @param gestureDuration
         */
        public boolean onSwipeDown(long gestureDuration);

        /**
         * Called when user swipes <b>left</b> with one finger
         * @return
         * @param gestureDuration
         */
        public boolean onSwipeLeft(long gestureDuration);

        /**
         * Called when user swipes <b>right</b> with one finger
         * @return
         * @param gestureDuration
         */
        public boolean onSwipeRight(long gestureDuration);

    }

    /**
     * Interface definition for the callback to be invoked when 2-finger gestures are performed
     */
    public interface On2FingerGestureListener {

        /**
         * Called when user swipes <b>up</b> with two fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeUp(long gestureDuration);

        /**
         * Called when user swipes <b>down</b> with two fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeDown(long gestureDuration);

        /**
         * Called when user swipes <b>left</b> with two fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeLeft(long gestureDuration);

        /**
         * Called when user swipes <b>right</b> with two fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeRight(long gestureDuration);

        /**
         * Called when user <b>pinches</b> with two fingers (bring together)
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onPinch(long gestureDuration);

        /**
         * Called when user <b>un-pinches</b> with two fingers (take apart)
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onUnpinch(long gestureDuration);

    }

    /**
     * Interface definition for the callback to be invoked when 3-finger gestures are performed
     */
    public interface On3FingerGestureListener {

        /**
         * Called when user swipes <b>up</b> with three fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeUp(long gestureDuration);

        /**
         * Called when user swipes <b>down</b> with three fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeDown(long gestureDuration);

        /**
         * Called when user swipes <b>left</b> with three fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeLeft(long gestureDuration);

        /**
         * Called when user swipes <b>right</b> with three fingers
         * @return
         * @param gestureDuration duration in milliSeconds
         */
        public boolean onSwipeRight(long gestureDuration);

    }
}
