package in.championswimmer.sfg.lib;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by championswimmer on 12/4/14.
 */
public class GestureView extends View implements View.OnTouchListener {
    public GestureView(Context context) {
        super(context);
    }

    public interface OnSimpleGestureListener {
        public boolean onPinch();
        public boolean onUnPinch();

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
