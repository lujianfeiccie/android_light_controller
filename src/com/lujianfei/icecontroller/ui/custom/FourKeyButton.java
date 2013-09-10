package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class FourKeyButton extends Button
{
  private String TAG = getClass().getSimpleName();
  private Drawable disBitmap;
  private Drawable downBitmap;
  private int height;
  public int innerRad = 0;
  private OnFourKeyTouchListener keyTouchListener;
  private Drawable leftBitmap;
  private Drawable normalBitmap;
  public int outerRad = 0;
  private Drawable rightBitmap;
  private Drawable upBitmap;
  private int width;
  private boolean down = false;
  private boolean leftSelected = true;
  public FourKeyButton(Context paramContext)
  {
    super(paramContext);
  }

  public FourKeyButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initBitmap(paramAttributeSet);
  }

  public FourKeyButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initBitmap(paramAttributeSet);
  }

  private float calDistanceFromCenter(float paramFloat1, float paramFloat2)
  {
    return (float)Math.sqrt((paramFloat1 - this.width / 2) * (paramFloat1 - this.width / 2) + (paramFloat2 - this.height / 2) * (paramFloat2 - this.height / 2));
  }

  private float getRad(float paramFloat1, float paramFloat2)
  {
    float f1 = calDistanceFromCenter(paramFloat1, paramFloat2);
    float f2 = (float)Math.acos((paramFloat1 - this.width / 2) / f1);
    if (this.height / 2 > paramFloat2)
      f2 = -f2;
    Log.i(this.TAG, "Rad is " + f2);
    return f2;
  }


  private void initBitmap(AttributeSet paramAttributeSet)
  {
    int disable = paramAttributeSet.getAttributeResourceValue(null, "disableBackground", 0);
    if (disable!= 0){
      this.disBitmap = getResources().getDrawable(disable);
    }
    int normal = paramAttributeSet.getAttributeResourceValue(null, "normalBackground", 0);
    if (normal != 0)
    {
      this.normalBitmap = getResources().getDrawable(normal);
    }
    int up = paramAttributeSet.getAttributeResourceValue(null, "upBackground", 0);
    if (up != 0){
      this.upBitmap = getResources().getDrawable(up);
    }
    int down = paramAttributeSet.getAttributeResourceValue(null, "downBackground", 0);
    if (down != 0){
      this.downBitmap = getResources().getDrawable(down);
    }
    int left = paramAttributeSet.getAttributeResourceValue(null, "leftBackground", 0);
    if (left != 0){
      this.leftBitmap = getResources().getDrawable(left);
    }
    int right = paramAttributeSet.getAttributeResourceValue(null, "rightBackground", 0);
    if (right != 0){
      this.rightBitmap = getResources().getDrawable(right);
    }
    setBackgroundDrawable(this.normalBitmap);
  }

  private void initData()
  {
    this.width = getWidth();
    this.height = getHeight();
    this.outerRad = Math.min(1 * this.width / 2, 1 * this.height / 2);
    this.innerRad = (1 * this.outerRad / 2);
  }

  private boolean isInrange(float paramFloat1, float paramFloat2)
  {
        float dist = calDistanceFromCenter(paramFloat1, paramFloat2);
        if(dist > innerRad && dist< outerRad){
        	return true;
        }
    return false;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    initData();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled())
      return super.onTouchEvent(paramMotionEvent);
    
    if(keyTouchListener==null){
    	return false;
    }
    float x = paramMotionEvent.getX();
    float y = paramMotionEvent.getY();
    
    switch (paramMotionEvent.getAction()) {
	case MotionEvent.ACTION_DOWN:
		down = true;
		doHandleDown(x,y);
		break;
	case MotionEvent.ACTION_UP:
		if(down){
			doHandleUp(x,y);
			down = false;
		}
		break;
	default:
		break;
	}
  
    return true;
  }
  void doHandleDown(float x,float y){
	  if(isInrange(x,y)){
	    	float horizontalY_start = (float)height/4.0f;
	    	float horizontalY_end = (float)height*3.0f/4.0f;
	    	float verticalX_start = (float)width/4.0f;
	    	float verticalX_end = (float)width*3.0f/4.0f;
	    	if((x> verticalX_start && x < verticalX_end) &&
	    		(y>0 && y< (height/4.0f))){
	    		log("up");
	    		setBackgroundDrawable(upBitmap); //ÉÏ¼ü
	    		keyTouchListener.onUpKeyTouchDown(this);
	    	}else if((x> verticalX_start && x < verticalX_end) &&
	        		(y>(height * 3.0f/4.0f) && y< height)){
	    		log("down");
	    		setBackgroundDrawable(downBitmap); //ÏÂ¼ü
	    		keyTouchListener.onDownKeyTouchDown(this);
	    	}else if((y> horizontalY_start && y < horizontalY_end) &&
	        		(x>0 && x< (width / 4.0f))){
	    		log("left");
	    		setBackgroundDrawable(leftBitmap); //×ó¼ü
	    		leftSelected = true;
	    		keyTouchListener.onLeftKeyTouchDown(this);
	    	}else if((y> horizontalY_start && y < horizontalY_end) &&
	        		(x>(width*3.0f/4.0f) && x< width)){
	    		log("right");
	    		setBackgroundDrawable(rightBitmap);//ÓÒ¼ü
	    		leftSelected = false;
	    		keyTouchListener.onRightKeyTouchDown(this);
	    	}
	    }else{
	    	log("isOut");
	    }
 }
 void doHandleUp(float x,float y){
	  if(isInrange(x,y)){
	    	float horizontalY_start = (float)height/4.0f;
	    	float horizontalY_end = (float)height*3.0f/4.0f;
	    	float verticalX_start = (float)width/4.0f;
	    	float verticalX_end = (float)width*3.0f/4.0f;
	    	if((x> verticalX_start && x < verticalX_end) &&
	    		(y>0 && y< (height/4.0f))){
	    		log("up");
	    		if(leftSelected){
	    		setBackgroundDrawable(leftBitmap);
	    		}else{
	    		setBackgroundDrawable(rightBitmap);	
	    		}
	    		keyTouchListener.onUpKeyTouchUp(this);
	    	}else if((x> verticalX_start && x < verticalX_end) &&
	        		(y>(height * 3.0f/4.0f) && y< height)){
	    		log("down");
	    		if(leftSelected){
		    		setBackgroundDrawable(leftBitmap);
		    	}else{
		    		setBackgroundDrawable(rightBitmap);	
		    	}
	    		keyTouchListener.onDownKeyTouchUp(this);
	    	}else if((y> horizontalY_start && y < horizontalY_end) &&
	        		(x>0 && x< (width / 4.0f))){
	    		log("left");
	    		keyTouchListener.onLeftKeyTouchUp(this);
	    	}else if((y> horizontalY_start && y < horizontalY_end) &&
	        		(x>(width*3.0f/4.0f) && x< width)){
	    		log("right");
	    		keyTouchListener.onRightKeyTouchUp(this);
	    	}
	    }else{
	    	log("isOut");
	    }
 }
  public void setOnFourKeyTouchListener(OnFourKeyTouchListener paramOnFourKeyTouchListener)
  {
    this.keyTouchListener = paramOnFourKeyTouchListener;
  }

  public static abstract interface OnFourKeyTouchListener
  {
    public abstract void onKeyTouchEnd(FourKeyButton paramFourKeyButton);
    public abstract void onDownKeyTouchDown(FourKeyButton paramFourKeyButton);
    public abstract void onLeftKeyTouchDown(FourKeyButton paramFourKeyButton);
    public abstract void onRightKeyTouchDown(FourKeyButton paramFourKeyButton);
    public abstract void onUpKeyTouchDown(FourKeyButton paramFourKeyButton);
    public abstract void onDownKeyTouchUp(FourKeyButton paramFourKeyButton);
    public abstract void onLeftKeyTouchUp(FourKeyButton paramFourKeyButton);
    public abstract void onRightKeyTouchUp(FourKeyButton paramFourKeyButton);
    public abstract void onUpKeyTouchUp(FourKeyButton paramFourKeyButton);
  }
  
  void log(String msg){
	  Log.d(TAG,msg);
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-æŠ?œ¯éƒ¨\S-è‹è¾¾æ­¦éƒ¨é—¨ç»ç†\D-å¾…ç ´è§£\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.custom.FourKeyButton
 * JD-Core Version:    0.6.2
 */