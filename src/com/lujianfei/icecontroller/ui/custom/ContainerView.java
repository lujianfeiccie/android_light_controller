package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ContainerView extends ViewGroup
{
  private static final int SNAP_VELOCITY = 600;
  private static final int TOUCH_STATE_REST = 0;
  private static final int TOUCH_STATE_SCROLLING = 1;
  private String TAG = getClass().getSimpleName();
  private OnPageChangeListener changeListener;
  private int mCurScreen;
  private int mDefaultScreen = 0;
  private float mLastMotionX;
  private int mTouchState = 0;
  private Scroller scroller;
  private VelocityTracker tracker;

  public ContainerView(Context paramContext)
  {
    super(paramContext);
    initData(paramContext);
  }

  public ContainerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initData(paramContext);
  }

  public ContainerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initData(paramContext);
  }

  private void initData(Context paramContext)
  {
    this.scroller = new Scroller(paramContext);
    this.mCurScreen = this.mDefaultScreen;
    if (this.changeListener != null)
      this.changeListener.onPageChane(this.mCurScreen);
  }

  public void computeScroll()
  {
    if (this.scroller.computeScrollOffset())
    {
      scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
      postInvalidate();
    }
  }

  public int getCurScreen()
  {
    return this.mCurScreen;
  }

  public boolean isScrolling()
  {
    return this.mTouchState == 1;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    int j = 0;
    if (paramBoolean)
    {
      i = 0;
      j = getChildCount();
    }
    for (int k = 0; ; k++)
    {
      if (k >= j)
        return;
      View localView = getChildAt(k);
      if (localView.getVisibility() != 8)
      {
        int m = localView.getMeasuredWidth();
        localView.layout(i, 0, i + m, localView.getMeasuredHeight());
        i += m;
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    if (View.MeasureSpec.getMode(paramInt1) != View.MeasureSpec.EXACTLY)
      throw new IllegalStateException("ContainerView only can run at EXACTLY mode");
    if (View.MeasureSpec.getMode(paramInt2) != View.MeasureSpec.EXACTLY)
      throw new IllegalStateException("ContainerView only can run at EXACTLY mode!");
    int j = getChildCount();
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        scrollTo(i * this.mCurScreen, 0);
        if (this.changeListener != null)
          this.changeListener.onPageChane(this.mCurScreen);
        return;
      }
      getChildAt(k).measure(paramInt1, paramInt2);
    }
  }
  @Override  
  public boolean onTouchEvent(MotionEvent event) {  
      // TODO Auto-generated method stub                            
          final int action = event.getAction();      
          final float x = event.getX();      
          final float y = event.getY();                     
          switch (action) {      
          case MotionEvent.ACTION_DOWN:                 
                log("onTouchEvent  ACTION_DOWN");                   
              if (tracker == null) {      
                      tracker = VelocityTracker.obtain();      
                      tracker.addMovement(event);   
              }              
              if (!scroller.isFinished()){      
            	  scroller.abortAnimation();      
              }                  
              mLastMotionX = x;                
              break;                        
          case MotionEvent.ACTION_MOVE:    
             int deltaX = (int)(mLastMotionX - x);                 
             if (IsCanMove(deltaX)){  
               if (tracker != null){  
                      tracker.addMovement(event);   
               }     
              mLastMotionX = x;       
              scrollBy(deltaX, 0);      
             }         
             break;                         
          case MotionEvent.ACTION_UP:                       
              int velocityX = 0;  
              if (tracker != null){  
                  tracker.addMovement(event);   
                  tracker.computeCurrentVelocity(1000);    
                  velocityX = (int) tracker.getXVelocity();  
              }                                     
              if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {               
                  Log.e(TAG, "snap left");      
                  snapToScreen(mCurScreen - 1);         
              } else if (velocityX < -SNAP_VELOCITY         
                      && mCurScreen < getChildCount() - 1) {             
                  Log.e(TAG, "snap right");      
                  snapToScreen(mCurScreen + 1);         
              } else {         
                  snapToDestination();         
              }                                     
              if (tracker != null) {         
                  tracker.recycle();         
                  tracker = null;         
              }         
              break;        
          }                     
          return true;      
  }  
  private boolean IsCanMove(int deltaX)  
  {  
      if (getScrollX() <= 0 && deltaX < 0 ){  
          return false;  
      }     
      if  (getScrollX() >=  (getChildCount() - 1) * getWidth() && deltaX > 0){  
          return false;  
      }         
      return true;  
  }  




  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.changeListener = paramOnPageChangeListener;
  }

  public void setToScreen(int paramInt)
  {
    int i = Math.max(0, Math.min(paramInt, -1 + getChildCount()));
    this.mCurScreen = i;
    scrollTo(i * getWidth(), 0);
    if (this.changeListener != null)
      this.changeListener.onPageChane(i);
  }

  public void snapToDestination()
  {
    int i = getWidth();
    snapToScreen((getScrollX() + i / 2) / i);
  }

  public void snapToScreen(int paramInt)
  {
    int i = Math.max(0, Math.min(paramInt, -1 + getChildCount()));
    if (getScrollX() != i * getWidth())
    {
      int j = i * getWidth() - getScrollX();
      this.scroller.startScroll(getScrollX(), 0, j, 0, 2 * Math.abs(j));
      this.mCurScreen = i;
      invalidate();
      if (this.changeListener != null)
        this.changeListener.onPageChane(i);
    }
  }

  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageChane(int paramInt);
  }
  void log(String msg){
	  Log.d(TAG,msg);
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-技术部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.custom.ContainerView
 * JD-Core Version:    0.6.2
 */