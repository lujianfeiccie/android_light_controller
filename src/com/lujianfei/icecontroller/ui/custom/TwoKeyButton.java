package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class TwoKeyButton extends Button {
	private boolean stateOn = false;
	private Drawable onBackground,offBackground,normalBackground;
	public TwoKeyButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public TwoKeyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initBitmap(attrs);
	}
	public TwoKeyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initBitmap(attrs);
	}
	private void initBitmap(AttributeSet paramAttributeSet)
	  {
	    int on = paramAttributeSet.getAttributeResourceValue(null, "onBackground", 0);
	    if (on!= 0)
	    {
	    	this.onBackground = getResources().getDrawable(on);
	    }
	    int off = paramAttributeSet.getAttributeResourceValue(null, "offBackground", 0);
	    if (off != 0)
	    {
	      this.offBackground = getResources().getDrawable(off);
	    }
	    int normal = paramAttributeSet.getAttributeResourceValue(null, "normalBackground", 0);
	    if (normal != 0)
	    {
	      this.normalBackground = getResources().getDrawable(normal);
	    }
	    setBackgroundDrawable(this.normalBackground);
	  }
	public boolean isStateOn() {
		return stateOn;
	}
	public void setStateOn(boolean stateOn) {
		this.stateOn = stateOn;
		updateState();
	}
	public void toggle() {
		if(stateOn){
			stateOn = false;
		}else{
			stateOn = true;
		}
		updateState();
	}
	void updateState(){
		if(stateOn){
			setBackgroundDrawable(offBackground);
		}else{
			setBackgroundDrawable(onBackground);
		}
	}
}
