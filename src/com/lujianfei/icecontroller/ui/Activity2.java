package com.lujianfei.icecontroller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.custom.CircleImageView;
import com.lujianfei.icecontroller.ui.custom.CircleImageView.OnCircleImageViewChangeListener;
import com.lujianfei.icecontroller.ui.custom.RGBKeyButton;
import com.lujianfei.icecontroller.ui.custom.TwoKeyButton;
import com.lujianfei.icecontroller.ui.others.GlobalData;
import com.lujianfei.icecontroller.ui.util.Util;

public class Activity2 extends Activity implements OnClickListener,OnCircleImageViewChangeListener
{
	private String tag = getClass().getSimpleName();
	  public DisplayMetrics displayMetrics;
	  private int widthPx;
	  private int heightPx;
	  private TwoKeyButton btn_1;
	  private TwoKeyButton btn_2;
	  private TwoKeyButton btn_3;
	  private TwoKeyButton btn_4;
	  private Button btn_bright_down;
	  private Button btn_bright_up;
	  private Button btn_home;
	  private Button btn_off;
	  private Button btn_on;
	  private RGBKeyButton btn_rgb;
	  private CircleImageView circleImageView;
	  private RelativeLayout circleImageViewRl;
	  private SettingPopupWindow mPopupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		log("onCreate");
		  this.displayMetrics = new DisplayMetrics();
		    getWindowManager().getDefaultDisplay().getMetrics(this.displayMetrics);
		    this.widthPx = this.displayMetrics.widthPixels;
		    this.heightPx = this.displayMetrics.heightPixels;
		    float f1 = Util.convertPxOrDip(this.displayMetrics.density, this.widthPx);
		    float f2 = Util.convertPxOrDip(this.displayMetrics.density, this.heightPx);
		    log(" widthPx: " + this.widthPx + " widthdip: " + f1);
		    log(" heightPx: " + this.heightPx + " heightdip: " + f2);
		    if ((f1 >= 480.0F) && (this.widthPx >= 600))
		      setContentView(R.layout.activity2_galaxy3);
		    
		    GlobalData.activity2 = this;
		      if ((f1 >= 320.0F) && (this.widthPx >= 480))
		        setContentView(R.layout.activity2_galaxy3);
		      else
		    	  setContentView(R.layout.activity2_320);
		      setSize();
		      findView();
		    //  initData();
		      mPopupWindow = new SettingPopupWindow(this);
	}
	 private void findView() {
		// TODO Auto-generated method stub
		  this.btn_home = ((Button)findViewById(R.id.btn_home));
		  this.btn_home.setOnClickListener(this);
		    this.btn_on = ((Button)findViewById(R.id.btn_on));
		    this.btn_on.setOnClickListener(this);
		    this.btn_off = ((Button)findViewById(R.id.btn_off));
		    this.btn_off.setOnClickListener(this);
		    this.btn_bright_down = ((Button)findViewById(R.id.btn_bright_down));
		    this.btn_bright_down.setOnClickListener(this);
		    this.btn_bright_up = ((Button)findViewById(R.id.btn_bright_up));
		    this.btn_bright_up.setOnClickListener(this);
		    this.circleImageViewRl = ((RelativeLayout)findViewById(R.id.circleImageView_rl));
		    ViewGroup.LayoutParams localLayoutParams1 = this.btn_on.getLayoutParams();
		    localLayoutParams1.height = (94 * this.heightPx / 1240);
		    localLayoutParams1.width = (212 * this.widthPx / 826);
		    this.btn_on.setPadding(95, 50 * this.heightPx / 1240, this.btn_on.getPaddingRight(), this.btn_on.getPaddingBottom());
		    this.btn_on.setLayoutParams(localLayoutParams1);
		    this.btn_on.invalidate();
		    ViewGroup.LayoutParams localLayoutParams2 = this.btn_off.getLayoutParams();
		    localLayoutParams2.height = (94 * this.heightPx / 1240);
		    localLayoutParams2.width = (212 * this.widthPx / 826);
		    this.btn_off.setPadding(this.btn_off.getPaddingLeft(), 50 * this.heightPx / 1240, 95, this.btn_off.getPaddingBottom());
		    this.btn_off.setLayoutParams(localLayoutParams2);
		    this.btn_off.invalidate();
		    ViewGroup.LayoutParams localLayoutParams3 = this.btn_bright_down.getLayoutParams();
		    localLayoutParams3.height = (94 * this.heightPx / 1240);
		    localLayoutParams3.width = (212 * this.widthPx / 826);
		    this.btn_bright_down.setPadding(95, 50 * this.heightPx / 1240, this.btn_bright_down.getPaddingRight(), this.btn_bright_down.getPaddingBottom());
		    this.btn_bright_down.setLayoutParams(localLayoutParams3);
		    this.btn_bright_down.invalidate();
		    ViewGroup.LayoutParams localLayoutParams4 = this.btn_bright_up.getLayoutParams();
		    localLayoutParams4.height = (94 * this.heightPx / 1240);
		    localLayoutParams4.width = (212 * this.widthPx / 826);
		    this.btn_bright_up.setPadding(this.btn_bright_up.getPaddingLeft(), 50 * this.heightPx / 1240, 95, this.btn_bright_up.getPaddingBottom());
		    this.btn_bright_up.setLayoutParams(localLayoutParams4);
		    this.btn_bright_up.invalidate();
		    this.circleImageView = ((CircleImageView)findViewById(R.id.circleImageView));
		    this.circleImageView.setOnCircleImageViewChange(this);
		    this.btn_rgb = ((RGBKeyButton)findViewById(R.id.btn_rgb));
		    this.btn_rgb.setOnClickListener(this);
		    ViewGroup.LayoutParams localLayoutParams5 = this.circleImageViewRl.getLayoutParams();
		    localLayoutParams5.height = (570 * this.heightPx / 1240);
		    localLayoutParams5.width = (570 * this.heightPx / 1240);
		    this.circleImageViewRl.setPadding(this.circleImageViewRl.getPaddingLeft(), 0, this.circleImageViewRl.getPaddingRight(), this.circleImageViewRl.getPaddingBottom());
		    this.circleImageViewRl.setLayoutParams(localLayoutParams5);
		    this.circleImageViewRl.invalidate();
		    ViewGroup.LayoutParams localLayoutParams6 = this.circleImageView.getLayoutParams();
		    localLayoutParams6.height = (570 * this.heightPx / 1240);
		    localLayoutParams6.width = (570 * this.heightPx / 1240);
		    this.circleImageView.setLayoutParams(localLayoutParams6);
		    this.circleImageView.invalidate();
		    this.btn_1 = ((TwoKeyButton)findViewById(R.id.btn_1));
		    this.btn_1.setOnClickListener(this);
		    this.btn_2 = ((TwoKeyButton)findViewById(R.id.btn_2));
		    this.btn_2.setOnClickListener(this);
		    this.btn_3 = ((TwoKeyButton)findViewById(R.id.btn_3));
		    this.btn_3.setOnClickListener(this);
		    this.btn_4 = ((TwoKeyButton)findViewById(R.id.btn_4));
		    this.btn_4.setOnClickListener(this);
		    this.btn_1.setPadding(this.btn_1.getPaddingLeft(), this.btn_1.getPaddingTop(), this.btn_1.getPaddingRight(), 117 * this.heightPx / 1240);
		    ViewGroup.LayoutParams localLayoutParams7 = this.btn_1.getLayoutParams();
		    localLayoutParams7.height = (284 * this.heightPx / 1240);
		    localLayoutParams7.width = (117 * this.widthPx / 826);
		    this.btn_1.setLayoutParams(localLayoutParams7);
		    this.btn_1.invalidate();
		    int i = (2 * this.btn_2.getPaddingLeft() + this.btn_3.getPaddingLeft()) / 3;
		    this.btn_2.setPadding(i, this.btn_2.getPaddingTop(), this.btn_2.getPaddingRight(), 60 * this.heightPx / 1240);
		    ViewGroup.LayoutParams localLayoutParams8 = this.btn_2.getLayoutParams();
		    localLayoutParams8.height = (284 * this.heightPx / 1240);
		    localLayoutParams8.width = (117 * this.widthPx / 826);
		    this.btn_2.setLayoutParams(localLayoutParams8);
		    this.btn_2.invalidate();
		    ViewGroup.LayoutParams localLayoutParams9 = this.btn_3.getLayoutParams();
		    localLayoutParams9.height = (284 * this.heightPx / 1240);
		    localLayoutParams9.width = (117 * this.widthPx / 826);
		    this.btn_3.setPadding(i, this.btn_3.getPaddingTop(), this.btn_3.getPaddingRight(), 60 * this.heightPx / 1240);
		    this.btn_3.setLayoutParams(localLayoutParams9);
		    this.btn_3.invalidate();
		    ViewGroup.LayoutParams localLayoutParams10 = this.btn_4.getLayoutParams();
		    localLayoutParams10.height = (284 * this.heightPx / 1240);
		    localLayoutParams10.width = (117 * this.widthPx / 826);
		    this.btn_4.setPadding(this.btn_4.getPaddingLeft(), this.btn_4.getPaddingBottom(), this.btn_4.getPaddingRight(), 117 * this.heightPx / 1240);
		    this.btn_4.setLayoutParams(localLayoutParams10);
		    this.btn_4.invalidate();
	}
	private void setSize()
	  {
	    if (this.widthPx / this.heightPx > 0)
	    {
	      this.widthPx = (826 * this.heightPx / 1240);
	      return;
	    }
	    this.heightPx = (1240 * this.widthPx / 826);
	  }
	void log(String msg){
		Log.d(tag, msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_home:
				View view = findViewById(R.id.btn_home);
				mPopupWindow.getWindow().showAsDropDown(view, Util.DipToPixels(this, -30),Util.DipToPixels(this, 20));
				break;
			case R.id.btn_1:
				btn_1.toggle();
				log(""+btn_1.isStateOn());
				break;
			case R.id.btn_2:
				btn_2.toggle();
				log(""+btn_2.isStateOn());
				break;
			case R.id.btn_3:
				btn_3.toggle();
				log(""+btn_3.isStateOn());
				break;
			case R.id.btn_4:
				btn_4.toggle();
				log(""+btn_4.isStateOn());
				break;
			case R.id.btn_bright_down:
				log("btn_bright_down");
				break;
			case R.id.btn_bright_up:
				log("btn_bright_up");
				break;
			case R.id.btn_off:
				log("btn_off");
				break;
			case R.id.btn_on:
				log("btn_on");
				break;
			case R.id.btn_rgb:
				btn_rgb.toggle();
				switch(btn_rgb.getMode()){
				case RGBKeyButton.MODE_RGB:
					log("MODE_RGB");
					break;
				case RGBKeyButton.MODE_R:
					log("MODE_R");
					break;
				case RGBKeyButton.MODE_G:
					log("MODE_G");
					break;
				case RGBKeyButton.MODE_B:
					log("MODE_B");
					break;
				}
				break;
			default:
				break;
			}
	}
	/*	  private Button btn_bright_down;
	  private Button btn_bright_up;
	  private Button btn_home;
	  private Button btn_off;
	  private Button btn_on;
	  private Button btn_rgb;*/
	@Override
	public void onTouchDown(CircleImageView paramCircleImageView) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTouchMove(CircleImageView paramCircleImageView) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTouchUp(CircleImageView paramCircleImageView) {
		// TODO Auto-generated method stub
		
	}
}