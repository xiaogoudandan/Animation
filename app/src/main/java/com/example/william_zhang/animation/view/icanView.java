package com.example.william_zhang.animation.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.william_zhang.animation.BezierEvaluator;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by william_zhang on 2017/8/22.
 */

public class IcanView extends RelativeLayout {
    private int mWidth,mHeight,icanWidth,icanHeight;
    private Interpolator[] interpolators;
    public IcanView(Context context) {
        this(context,null);
    }

    public IcanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IcanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public IcanView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        initInterpolator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
//        int width=MeasureSpec.getSize(widthMeasureSpec);
//        int height=MeasureSpec.getSize(heightMeasureSpec);
//        if(widthMode==MeasureSpec.EXACTLY)//具体数值或者是matchment
//        {
//              mWidth=getMeasuredWidth();
//        }else {//wrapmant
//              mWidth=width;
//        }
//        if(heightMode==MeasureSpec.EXACTLY)//具体数值或者是matchment
//        {
//            mHeight=getMeasuredHeight();
//        }else {//wrapmant
//           mHeight=height;
//        }
//        setMeasuredDimension(mWidth,mHeight);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
    }
    private Random random = new Random();
    @Override
    protected void onDetachedFromWindow() {
        removeAllViews();
        super.onDetachedFromWindow();
    }
    private void initInterpolator() {
        interpolators = new Interpolator[]{
                new LinearInterpolator(),
                new AccelerateDecelerateInterpolator(),
                new AccelerateInterpolator(),
                new DecelerateInterpolator(),
        };
    }
    public void addLoveIcon(int resId) {
        ImageView view = new ImageView(getContext());
        view.setImageResource(resId);
        icanWidth=view.getWidth();
        icanHeight=view.getHeight();
        addView(view);
        startAnimator(view);
    }

//    private void startAnimatior(ImageView view) {
//        //曲线的两个顶点
//        PointF pointF1=new PointF(random.nextInt(mWidth),
//                random.nextInt(mHeight / 2) + mHeight / 2);
//        PointF pointF2 = new PointF(
//                random.nextInt(mWidth),
//                random.nextInt(mHeight / 2));
//        PointF pointStart = new PointF((mWidth - 0) / 2,
//                mHeight - 0);
//        PointF pointEnd = new PointF(random.nextInt(mWidth), random.nextInt(mHeight / 2));
//
//        //贝塞尔估值器
//        BezierEvaluator evaluator=new BezierEvaluator(pointF1,pointF2);
//        ValueAnimator animtor=ValueAnimator.ofObject(evaluator,pointStart,pointEnd);
//        animtor.setTarget(view);
//        animtor.setDuration(3000);
//        animtor.addUpdateListener(new UpdateListener(view));
//        animtor.addListener(new AnimatorListener(view,this));
//        animtor.setInterpolator(interpolators[random.nextInt(4)]);
//        animtor.start();
//    }
private void startAnimator(ImageView view) {
    //曲线的两个顶点
    PointF pointF1 = new PointF(
            random.nextInt(mWidth),
            random.nextInt(mHeight / 2) + mHeight / 2);
    PointF pointF2 = new PointF(
            random.nextInt(mWidth),
            random.nextInt(mHeight / 2));
    PointF pointStart = new PointF((mWidth - icanWidth) / 2,
            mHeight - icanHeight);
    PointF pointEnd = new PointF(random.nextInt(mWidth), random.nextInt(mHeight / 2));

    //贝塞尔估值器
    BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
    ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointStart, pointEnd);
    animator.setTarget(view);
    animator.setDuration(3000);
    animator.addUpdateListener(new UpdateListener(view));
    animator.addListener(new AnimatorListener(view, this));
    animator.setInterpolator(interpolators[random.nextInt(4)]);

    animator.start();
}


    public static class UpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private WeakReference<ImageView> iv;

        public UpdateListener(ImageView iv) {
            this.iv = new WeakReference<>(iv);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF pointF = (PointF) animation.getAnimatedValue();
            ImageView view = iv.get();
            if (null != view) {
                view.setX(pointF.x);
                view.setY(pointF.y);
                view.setAlpha(1 - animation.getAnimatedFraction() + 0.1f);
            }
        }
    }

    public static class AnimatorListener extends AnimatorListenerAdapter {

        private WeakReference<ImageView> iv;
        private WeakReference<IcanView> parent;

        public AnimatorListener(ImageView iv, IcanView parent) {
            this.iv = new WeakReference<>(iv);
            this.parent = new WeakReference<>(parent);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            ImageView view = iv.get();
            IcanView parent = this.parent.get();
            if (null != view
                    && null != parent) {
                parent.removeView(view);
            }
        }
    }

}
