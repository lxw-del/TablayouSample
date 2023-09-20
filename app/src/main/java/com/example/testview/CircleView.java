package com.example.testview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CircleView extends View {

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                      int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //抗锯齿
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path path = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        //paint.setAntiAlias(true);
        //canvas.drawCircle(300,300,200,paint);
        /*canvas.drawColor(Color.parseColor("#88880000"));*/


        //canvas.drawRoundRect(700,700,1000,900,60,70,paint);
        path.lineTo(100,100);
        //path.rLineTo(0,100);
        //canvas.drawArc(200,100,800,500,-110,100,false,paint);

        path.arcTo(100,100,300,300,-90,90,true);
        canvas.drawPath(path,paint);

    }
}
