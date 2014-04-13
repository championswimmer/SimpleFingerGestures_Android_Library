package in.championswimmer.sfg.lib;

import android.view.MotionEvent;

/**
 * Internal API class to analyse the recorded gestures.
 *
 * @author championswimmer
 * @since 0.1 12/04/14
 * @version 0.2
 *
 */
public class GestureAnalyser {

    private static final String TAG = "GestureAnalyser";
    public static final boolean DEBUG = true;

    public static final int SWIPE_1_UP = 11;
    public static final int SWIPE_1_DOWN = 12;
    public static final int SWIPE_1_LEFT = 13;
    public static final int SWIPE_1_RIGHT = 14;
    public static final int SWIPE_2_UP = 21;
    public static final int SWIPE_2_DOWN = 22;
    public static final int SWIPE_2_LEFT = 23;
    public static final int SWIPE_2_RIGHT = 24;

    private float[] initialX = new float[2];
    private float[] initialY = new float[2];
    private float[] finalX = new float[2];
    private float[] finalY = new float[2];
    private float[] delX = new float[2];
    private float[] delY = new float[3];

    private int numFingers = 0;

    public GestureAnalyser() {
    }

    public void trackGesture(MotionEvent ev) {
        int n = ev.getPointerCount();
        for (int i = 0; i < n; i++) {
            initialX[i] = ev.getX(i);
            initialY[i] = ev.getY(i);
        }
        numFingers = n;
    }

    public void untrackGesture() {
        numFingers = 0;
    }

    public int getGesture(MotionEvent ev) {
        for (int i = 0; i < numFingers; i++) {
            finalX[i] = ev.getX(i);
            finalY[i] = ev.getY(i);
            delX[i] = finalX[i] - initialX[i];
            delY[i] = finalY[i] - initialY[i];
        }
        return calcGesture();
    }

    private int calcGesture() {
        if (numFingers == 1) {
            if ((-(delY[0])) > (2 * (Math.abs(delX[0])))) {
                return SWIPE_1_UP;
            }

            if (((delY[0])) > (2 * (Math.abs(delX[0])))) {
                return SWIPE_1_DOWN;
            }

            if ((-(delX[0])) > (2 * (Math.abs(delY[0])))) {
                return SWIPE_1_LEFT;
            }

            if (((delX[0])) > (2 * (Math.abs(delY[0])))) {
                return SWIPE_1_RIGHT;
            }
        }
        if (numFingers == 2) {
            if (((-delY[0]) > (2 * Math.abs(delX[0]))) && ((-delY[1]) > (2 * Math.abs(delX[1])))) {
                return SWIPE_2_UP;
            }
            if (((delY[0]) > (2 * Math.abs(delX[0]))) && ((delY[1]) > (2 * Math.abs(delX[1])))) {
                return SWIPE_2_DOWN;
            }
            if (((-delX[0]) > (2 * Math.abs(delY[0]))) && ((-delX[1]) > (2 * Math.abs(delY[1])))) {
                return SWIPE_2_LEFT;
            }
            if (((delX[0]) > (2 * Math.abs(delY[0]))) && ((delX[1]) > (2 * Math.abs(delY[1])))) {
                return SWIPE_2_RIGHT;
            }
        }
        return 0;
    }


}
