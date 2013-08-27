package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lujianfei.icecontroller.R;

public class CircleImageView extends RelativeLayout
{
  private final String TAG = getClass().getSimpleName();
  private ImageView bigCircle;
  private Bitmap big_circle_bitmap;
  private byte byteValue;
  private OnCircleImageViewChangeListener change;
  private int colorValue = -1;
  private Context context;
  private int height;
  public int innerRad = 0;
  public int outerRad = 0;
  private int resId = -1;
  private ImageView smallCircle;
  private Bitmap small_circle_bitmap;
  private int width;

  public CircleImageView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.resId = R.drawable.circle_back2;
    this.big_circle_bitmap = BitmapFactory.decodeResource(getResources(), this.resId);
    this.small_circle_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.small_circle);
    initView();
  }

  public CircleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.resId = paramAttributeSet.getAttributeResourceValue(null, "Circle", R.drawable.circle_back2);
    this.big_circle_bitmap = BitmapFactory.decodeResource(getResources(), this.resId);
    this.small_circle_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.small_circle);
    initView();
  }

  private float calCircleValue(float paramFloat)
  {
    if ((paramFloat >= -1.570796F) && (paramFloat <= 1.570796F));
    for (float f1 = paramFloat + 1.570796F; ; f1 = paramFloat - 3.0F * 1.570796F)
    {
      float f2 = (float)(128.0F * f1 / 3.141592653589793D);
      Log.i(this.TAG, "Value is " + f2);
      return f2;
    }
  }

  private float calDistanceFromCenter(float paramFloat1, float paramFloat2)
  {
    return (float)Math.sqrt((paramFloat1 - this.width / 2) * (paramFloat1 - this.width / 2) + (paramFloat2 - this.height / 2) * (paramFloat2 - this.height / 2));
  }

  private int getMaxX(float paramFloat)
  {
    return (int)(this.outerRad * Math.cos(paramFloat) + this.width / 2);
  }

  private int getMaxY(float paramFloat)
  {
    return (int)(this.outerRad * Math.sin(paramFloat) + this.height / 2);
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

  private void initData()
  {
    this.width = getWidth();
    this.height = getHeight();
    this.outerRad = Math.min(9 * this.width / 20, 9 * this.height / 20);
    this.innerRad = (12 * this.outerRad / 20);
  }

  private void initView()
  {
    this.smallCircle = new ImageView(this.context);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(20, 20);
    this.smallCircle.setLayoutParams(localLayoutParams);
    this.smallCircle.setImageBitmap(this.small_circle_bitmap);
    this.smallCircle.setVisibility(View.INVISIBLE);
    this.bigCircle = new ImageView(this.context);
    this.bigCircle.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.bigCircle.setImageBitmap(this.big_circle_bitmap);
    this.bigCircle.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
    {
      public boolean onPreDraw()
      {
        CircleImageView.this.bigCircle.getViewTreeObserver().removeOnPreDrawListener(this);
        CircleImageView.this.initData();
        int i = CircleImageView.this.width / 14;
        CircleImageView.this.smallCircle.layout(20, CircleImageView.this.width / 2 - i / 2, i + 20, i + CircleImageView.this.width / 2 - i / 2);
        return true;
      }
    });
    addView(this.bigCircle);
    addView(this.smallCircle);
  }

  private boolean isInrange(float paramFloat1, float paramFloat2)
  {
        float dist = calDistanceFromCenter(paramFloat1, paramFloat2);
        if(dist > innerRad && dist < outerRad){
        	return true;
        }
    return false;
  }

  private void setByteValue(byte paramByte)
  {
    this.byteValue = paramByte;
  }

  private void update(float paramFloat1, float paramFloat2)
  {
	  if(isInrange(paramFloat1, paramFloat2)){
	    updatePosition((int)paramFloat1, (int)paramFloat2);
	    updateValue(paramFloat1, paramFloat2);
	  }
  }

  private void updatePosition(int paramInt1, int paramInt2)
  {
    int i = this.smallCircle.getWidth() / 2;
    int j = this.smallCircle.getHeight() / 2;
    
    	this.smallCircle.setVisibility(View.VISIBLE);
    	this.smallCircle.layout(paramInt1 - i, paramInt2 - j, paramInt1 + i, paramInt2 + j);
  }

  private void updateValue(float paramFloat1, float paramFloat2)
  {
	  int i = (byte)(int)calCircleValue(getRad(paramFloat1, paramFloat2));
	    log( "Byte 0x" + Integer.toHexString(i & 0xFF));
	    setByteValue((byte)i);
	    int j = (int)(paramFloat1 * this.big_circle_bitmap.getWidth() / this.width);
	    int k = (int)(paramFloat2 * this.big_circle_bitmap.getHeight() / this.height);
	    if ((j >= 0) && (j < this.big_circle_bitmap.getWidth()) && (k >= 0) && (k < this.big_circle_bitmap.getHeight()))
	    {
	      this.colorValue = this.big_circle_bitmap.getPixel(j, k);
	      log("colorValue 0x" + Integer.toHexString(0xFF & this.colorValue));
	    }
  }

  public byte getByteValue()
  {
    return this.byteValue;
  }

  public int[] getRGB()
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = ((0xFF0000 & this.colorValue) >> 16);
    arrayOfInt[1] = ((0xFF00 & this.colorValue) >> 8);
    arrayOfInt[2] = (0xFF & this.colorValue);
    return arrayOfInt;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
	  boolean bool = true;
	    if (!isEnabled()){
	    	return false;
	    }
	    float x = paramMotionEvent.getX();
	    float y = paramMotionEvent.getY();
	    update(x,y);
	    
	    switch (paramMotionEvent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			change.onTouchDown(this);
			break;
		case MotionEvent.ACTION_MOVE:
			change.onTouchMove(this);
			break;
		case MotionEvent.ACTION_UP:
			change.onTouchUp(this);
			break;

		default:
			break;
		}
    return bool;
  }

  public void setOnCircleImageViewChange(OnCircleImageViewChangeListener paramOnCircleImageViewChangeListener)
  {
    this.change = paramOnCircleImageViewChangeListener;
  }

  public static abstract interface OnCircleImageViewChangeListener
  {
    public abstract void onTouchDown(CircleImageView paramCircleImageView);

    public abstract void onTouchMove(CircleImageView paramCircleImageView);

    public abstract void onTouchUp(CircleImageView paramCircleImageView);
  }
  void log(String msg){
	  Log.d(TAG, msg);
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-�?��部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.custom.CircleImageView
 * JD-Core Version:    0.6.2
 */