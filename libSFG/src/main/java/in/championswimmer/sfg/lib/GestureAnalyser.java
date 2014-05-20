package in.championswimmer.sfg.lib;

import android.os.SystemClock;
import android.view.MotionEvent;

/**
 * Internal API class to analyse the recorded gestures.
 *
 * @author championswimmer
 * @version 0.2
 * @since 0.1 12/04/14
 */
public class GestureAnalyser {

    public static final boolean DEBUG = true;
    // Finished gestures flags
    public static final int SWIPE_1_UP = 11;
    public static final int SWIPE_1_DOWN = 12;
    public static final int SWIPE_1_LEFT = 13;
    public static final int SWIPE_1_RIGHT = 14;
    public static final int SWIPE_2_UP = 21;
    public static final int SWIPE_2_DOWN = 22;
    public static final int SWIPE_2_LEFT = 23;
    public static final int SWIPE_2_RIGHT = 24;
    public static final int SWIPE_3_UP = 31;
    public static final int SWIPE_3_DOWN = 32;
    public static final int SWIPE_3_LEFT = 33;
    public static final int SWIPE_3_RIGHT = 34;
    public static final int SWIPE_4_UP = 41;
    public static final int SWIPE_4_DOWN = 42;
    public static final int SWIPE_4_LEFT = 43;
    public static final int SWIPE_4_RIGHT = 44;
    public static final int PINCH = 25;
    public static final int UNPINCH = 26;
    //Ongoing gesture flags
    public static final int SWIPING_1_UP = 101;
    public static final int SWIPING_1_DOWN = 102;
    public static final int SWIPING_1_LEFT = 103;
    public static final int SWIPING_1_RIGHT = 104;
    public static final int SWIPING_2_UP = 201;
    public static final int SWIPING_2_DOWN = 202;
    public static final int SWIPING_2_LEFT = 203;
    public static final int SWIPING_2_RIGHT = 204;
    public static final int PINCHING = 205;
    public static final int UNPINCHING = 206;
    private static final String TAG = "GestureAnalyser";
    private double[] initialX = new double[5];
    private double[] initialY = new double[5];
    private double[] finalX = new double[5];
    private double[] finalY = new double[5];
    private double[] currentX = new double[5];
    private double[] currentY = new double[5];
    private double[] delX = new double[5];
    private double[] delY = new double[5];
    private int numFingers = 0;
    private long initialT, finalT, currentT;

    public GestureAnalyser() {
    }

    public void trackGesture(MotionEvent ev) {
        int n = ev.getPointerCount();
        for (int i = 0; i < n; i++) {
            initialX[i] = ev.getX(i);
            initialY[i] = ev.getY(i);
        }
        numFingers = n;
        initialT = SystemClock.uptimeMillis();
    }

    public void untrackGesture() {
        numFingers = 0;
    }

    public GestureType getGesture(MotionEvent ev) {
        for (int i = 0; i < numFingers; i++) {
            finalX[i] = ev.getX(i);
            finalY[i] = ev.getY(i);
            delX[i] = finalX[i] - initialX[i];
            delY[i] = finalY[i] - initialY[i];
        }
        finalT = SystemClock.uptimeMillis();
        GestureType gt = new GestureType();
        gt.setGestureFlag(calcGesture());
        gt.setGestureDuration(finalT - initialT);
        return gt;
    }

    public int getOngoingGesture(MotionEvent ev) {
        for (int i = 0; i < numFingers; i++) {
            currentX[i] = ev.getX(i);
            currentY[i] = ev.getY(i);
            delX[i] = finalX[i] - initialX[i];
            delY[i] = finalY[i] - initialY[i];
        }
        currentT = SystemClock.uptimeMillis();
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
            if (finalFingDist(0, 1) > 2 * (initialFingDist(0, 1))) {
                return UNPINCH;
            }
            if (finalFingDist(0, 1) < 0.5 * (initialFingDist(0, 1))) {
                return PINCH;
            }
        }
        if (numFingers == 3) {
            if (((-delY[0]) > (2 * Math.abs(delX[0])))
                    && ((-delY[1]) > (2 * Math.abs(delX[1])))
                    && ((-delY[2]) > (2 * Math.abs(delX[2])))) {
                return SWIPE_3_UP;
            }
            if (((delY[0]) > (2 * Math.abs(delX[0])))
                    && ((delY[1]) > (2 * Math.abs(delX[1])))
                    && ((delY[2]) > (2 * Math.abs(delX[2])))) {
                return SWIPE_3_DOWN;
            }
            if (((-delX[0]) > (2 * Math.abs(delY[0])))
                    && ((-delX[1]) > (2 * Math.abs(delY[1])))
                    && ((-delX[2]) > (2 * Math.abs(delY[2])))) {
                return SWIPE_3_LEFT;
            }
            if (((delX[0]) > (2 * Math.abs(delY[0])))
                    && ((delX[1]) > (2 * Math.abs(delY[1])))
                    && ((delX[2]) > (2 * Math.abs(delY[2])))) {
                return SWIPE_3_RIGHT;
            }
            /*
            if (finalFingDist(0,1) > 2*(initialFingDist(0,1))) {
                return UNPINCH;
            }
            if (finalFingDist(0,1) < 0.5*(initialFingDist(0,1))) {
                return PINCH;
            }
            */
        }
        if (numFingers == 4) {
            if (((-delY[0]) > (2 * Math.abs(delX[0])))
                    && ((-delY[1]) > (2 * Math.abs(delX[1])))
                    && ((-delY[2]) > (2 * Math.abs(delX[2])))
                    && ((-delY[3]) > (2 * Math.abs(delX[3])))) {
                return SWIPE_4_UP;
            }
            if (((delY[0]) > (2 * Math.abs(delX[0])))
                    && ((delY[1]) > (2 * Math.abs(delX[1])))
                    && ((delY[2]) > (2 * Math.abs(delX[2])))
                    && ((delY[3]) > (2 * Math.abs(delX[3])))) {
                return SWIPE_4_DOWN;
            }
            if (((-delX[0]) > (2 * Math.abs(delY[0])))
                    && ((-delX[1]) > (2 * Math.abs(delY[1])))
                    && ((-delX[2]) > (2 * Math.abs(delY[2])))
                    && ((-delX[3]) > (2 * Math.abs(delY[3])))) {
                return SWIPE_4_LEFT;
            }
            if (((delX[0]) > (2 * Math.abs(delY[0])))
                    && ((delX[1]) > (2 * Math.abs(delY[1])))
                    && ((delX[2]) > (2 * Math.abs(delY[2])))
                    && ((delX[3]) > (2 * Math.abs(delY[3])))) {
                return SWIPE_4_RIGHT;
            }
            /*
            if (finalFingDist(0,1) > 2*(initialFingDist(0,1))) {
                return UNPINCH;
            }
            if (finalFingDist(0,1) < 0.5*(initialFingDist(0,1))) {
                return PINCH;
            }
            */
        }
        return 0;
    }

    private double initialFingDist(int fingNum1, int fingNum2) {

        return Math.sqrt(Math.pow((initialX[fingNum1] - initialX[fingNum2]), 2)
                + Math.pow((initialY[fingNum1] - initialY[fingNum2]), 2));
    }

    private double finalFingDist(int fingNum1, int fingNum2) {

        return Math.sqrt(Math.pow((finalX[fingNum1] - finalX[fingNum2]), 2)
                + Math.pow((finalY[fingNum1] - finalY[fingNum2]), 2));
    }

    public class GestureType {
        private int gestureFlag;
        private long gestureDuration;


        public long getGestureDuration() {
            return gestureDuration;
        }

        public void setGestureDuration(long gestureDuration) {
            this.gestureDuration = gestureDuration;
        }


        public int getGestureFlag() {
            return gestureFlag;
        }

        public void setGestureFlag(int gestureFlag) {
            this.gestureFlag = gestureFlag;
        }


    }


}
