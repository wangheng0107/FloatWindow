package com.example.lc.floatbutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class ViewDragHelperLayout extends LinearLayout {
  private final String TAG = this.getClass().getSimpleName();

  private ViewDragHelper viewDragHelper;

  private View topView, dragView, bottomView;

  // 拖拽View距离顶部的最小高度
  private final int MIN_TOP = 0;
  // 拖拽View的高度
  private int dragHeight;
  // 拖拽View实际可变化的高度
  private int dh;
  // 是否为第一次
  private boolean isFirst;

  public ViewDragHelperLayout(Context context) {
    this(context, null);
  }

  public ViewDragHelperLayout(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ViewDragHelperLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    isFirst = true;
    viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

      @Override
      public boolean tryCaptureView(@NonNull View view, int i) {
        return view == dragView;
      }

      @Override
      public int getViewVerticalDragRange(@NonNull View child) {
        return dh;
      }

      @Override
      public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
        // 顶部边界
        if (top < MIN_TOP) {
          top = MIN_TOP;
        } else if (top > dh + MIN_TOP) {
          top = dh + MIN_TOP;
        }
        return top;
      }

      @Override
      public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
        super.onViewPositionChanged(changedView, left, top, dx, dy);
        // 改变底部区域高度 上拉和下拉的速度太慢，以2倍速度拉伸
        LinearLayout.LayoutParams bottomViewLayoutParams = (LinearLayout.LayoutParams) bottomView.getLayoutParams();
        int bottomHeight = bottomView.getMeasuredHeight();
        bottomViewLayoutParams.height = bottomHeight + dy * -1;
        bottomView.setLayoutParams(bottomViewLayoutParams);
//        Log.e(TAG,)
        // 改变顶部区域高度 上拉和下拉的速度太慢，以2倍速度拉伸
        LinearLayout.LayoutParams topViewLayoutParams = (LinearLayout.LayoutParams) topView.getLayoutParams();
        int topHeight = topView.getMeasuredHeight();
        topViewLayoutParams.height = topHeight + dy;
        topView.setLayoutParams(topViewLayoutParams);
      }
    });
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (isFirst) {
      int totalHeight = getMeasuredHeight();
      int bottomHeight = bottomView.getMeasuredHeight();
      dragHeight = dragView.getMeasuredHeight();
      dh = totalHeight - dragHeight - MIN_TOP;
      isFirst = false;
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    return viewDragHelper.shouldInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    viewDragHelper.processTouchEvent(event);
    return true;
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    topView = getChildAt(0);
    dragView = getChildAt(1);
    bottomView = getChildAt(2);
  }
}

