package com.example.testview;

import android.app.ActionBar;
import android.content.Context;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomerView extends View {
    private int lastX;
    private int lastY;
    public CustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomerView(Context context) {
        super(context);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                /*offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offsetX;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);
                break;
        }
        return true;
    }
}
