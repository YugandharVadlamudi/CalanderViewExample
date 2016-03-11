package com.example.kiran.calanderviewexample;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Kiran on 10-03-2016.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureLisener());
    }

    public void onSwipeLeft() {

    }

    public void onSwipeRight() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView tv=(TextView) v.findViewById(R.id.tv_current_month);
        Log.e("gestureView",""+tv.getText());
        return gestureDetector.onTouchEvent(event);
    }
//    detect the motion of the event on the screen
    public class GestureLisener extends GestureDetector.SimpleOnGestureListener
    {
        public static final int SWIPE_DISANCE_THRESHOULD=100;
        public static final int SWIPE_VELOCITY_TRESHOULD=100;
//down press event occures
        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX=e2.getX()-e1.getX();
            float distanceY=e2.getY()-e1.getY();
            if(Math.abs(distanceX)>Math.abs(distanceY)&&Math.abs(distanceX)>SWIPE_DISANCE_THRESHOULD&&Math.abs(velocityX)>SWIPE_VELOCITY_TRESHOULD)
            {
                if(distanceX>0)
                    onSwipeLeft();
                else
                    onSwipeRight();
                return true;
            }
            return false;
        }
    }
}
