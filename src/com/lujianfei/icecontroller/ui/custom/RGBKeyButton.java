package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class RGBKeyButton extends Button {
	private boolean rgbMode = true;
	private int mode = 1;
	private int currentMode = 0;
	public static final int MODE_RGB = 0;
	public static final int MODE_R = 1;
	public static final int MODE_G = 2;
	public static final int MODE_B = 3;
	public int getMode() {
		return currentMode;
	}
	public void setMode(int mode) {
		this.mode = mode;
		updateState();
	}
	private Drawable rBackground,gBackground,bBackground,rgbBackground;
	public boolean isRgbMode() {
		return rgbMode;
	}
	public RGBKeyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public RGBKeyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initBitmap(attrs);
	}
	public RGBKeyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initBitmap(attrs);
	}
	private void initBitmap(AttributeSet paramAttributeSet)
	  {
	    int r = paramAttributeSet.getAttributeResourceValue(null, "rBackground", 0);
	    if(r!=0){
	    	this.rBackground= getResources().getDrawable(r);
	    }
	    int g = paramAttributeSet.getAttributeResourceValue(null, "gBackground", 0);
	    if(g!=0){
	    	this.gBackground= getResources().getDrawable(g);
	    }
	    int b = paramAttributeSet.getAttributeResourceValue(null, "bBackground", 0);
	    if(b!=0){
	    	this.bBackground= getResources().getDrawable(b);
	    }
	    int rgb = paramAttributeSet.getAttributeResourceValue(null, "rgbBackground", 0);
	    if(rgb!=0){
	    	this.rgbBackground= getResources().getDrawable(rgb);
	    }
	    setBackgroundDrawable(this.rgbBackground);
	  }
	public void toggle(){
		mode = mode%4;
		updateState();
		currentMode = mode++;
	}
	void updateState(){
		switch (mode) {
		case MODE_RGB:
			setBackgroundDrawable(rgbBackground);
			rgbMode = true;
			break;
		case MODE_R:
			setBackgroundDrawable(rBackground);
			rgbMode = false;
			break;
		case MODE_G:
			setBackgroundDrawable(gBackground);
			rgbMode = false;
			break;
		case MODE_B:
			setBackgroundDrawable(bBackground);
			rgbMode = false;
			break;
		default:
			break;
		}
	}
}
