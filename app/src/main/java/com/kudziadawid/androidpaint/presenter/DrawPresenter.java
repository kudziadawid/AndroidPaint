package com.kudziadawid.androidpaint.presenter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

import com.kudziadawid.androidpaint.contract.DrawContract;
import com.kudziadawid.androidpaint.model.DrawModel;

public class DrawPresenter extends BasePresenter<DrawContract.View> implements DrawContract.Presenter {

    private DrawModel drawModel = new DrawModel();

    public void drawViewCreate() {

        drawModel.getPaint().setStyle(Paint.Style.STROKE);
        drawModel.getPaint().setStrokeJoin(Paint.Join.ROUND);
        drawModel.getPaint().setStrokeCap(Paint.Cap.ROUND);
        drawModel.getPaint().setStrokeWidth(10);
    }

    public Paint getPaint() {

        return drawModel.getPaint();
    }
}
