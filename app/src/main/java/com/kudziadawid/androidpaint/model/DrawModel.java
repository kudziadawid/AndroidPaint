package com.kudziadawid.androidpaint.model;

import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class DrawModel {

    private Paint paint = new Paint();
    private Point point = new Point();
    private List<Point> points = new ArrayList<>();

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
