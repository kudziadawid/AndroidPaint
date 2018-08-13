package com.kudziadawid.androidpaint.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.kudziadawid.androidpaint.presenter.DrawPresenter;

public class DrawView extends View{

    private DrawPresenter drawPresenter;
    private Path path;

    public DrawView(Context context) {
        super(context);
        drawPresenter = new DrawPresenter();
        drawPresenter.drawViewCreate();
        setFocusable(true);
        setFocusableInTouchMode(true);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, drawPresenter.getPaint());
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
