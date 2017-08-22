package com.example.william_zhang.animation;

import android.animation.TypeEvaluator;
import android.graphics.PointF;



public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF point1, point2;
    private PointF point;

    public BezierEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
        point = new PointF();
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        point.x = startValue.x * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.x * t * (1 - t) * (1 - t)
                + 3 * point2.x * t * t * (1 - t)
                + endValue.x * t * t * t;
        point.y = startValue.y * (1 - t) * (1 - t) * (1 - t)
                + 3 * point1.y * t * (1 - t) * (1 - t)
                + 3 * point2.y * t * t * (1 - t)
                + endValue.y * t * t * t;
        return point;
    }
}
