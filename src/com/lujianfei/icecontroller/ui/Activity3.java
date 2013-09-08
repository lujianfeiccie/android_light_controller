package com.lujianfei.icecontroller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.custom.TwoKeyButton;
import com.lujianfei.icecontroller.ui.others.GlobalData;
import com.lujianfei.icecontroller.ui.util.Util;

public class Activity3 extends BaseActivity implements OnClickListener
{
	private String tag = getClass().getSimpleName();
	public DisplayMetrics displayMetrics;
	 private TwoKeyButton btn_1;
	  private TwoKeyButton btn_2;
	  private TwoKeyButton btn_3;
	  private TwoKeyButton btn_4;
	  
	  private Button btn_alarm;
	  private Button btn_home;
	  private Button btn_meeting;
	  private Button btn_mode;
	  private Button btn_night;
	  private Button btn_off;
	  private Button btn_on;
	  private Button btn_reading;
	  private Button btn_recreation;
	  private Button btn_sleep;
	  private Button btn_timer;
	  private LinearLayout menuLl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		log("onCreate");
		      setContentView(R.layout.activity3);
		    GlobalData.activity3= this;
		      findView();
		    //  initData();
	}
	private void findView() {
		// TODO Auto-generated method stub
		 this.btn_home = ((Button)findViewById(R.id.btn_home));
		  this.btn_home.setOnClickListener(this);
		    this.btn_on = ((Button)findViewById(R.id.btn_on));
		    this.btn_on.setOnClickListener(this);
		    this.btn_off = ((Button)findViewById(R.id.btn_off));
		    this.btn_off.setOnClickListener(this);
//		    ViewGroup.LayoutParams localLayoutParams2 = this.btn_off.getLayoutParams();
//		    localLayoutParams2.height = (93 * this.heightPx / 1240);
//		    localLayoutParams2.width = (215 * this.widthPx / 826);
//		    this.btn_off.setLayoutParams(localLayoutParams2);
//		    this.btn_off.invalidate();
		    this.menuLl = ((LinearLayout)findViewById(R.id.menu_ll));
//		    ViewGroup.LayoutParams localLayoutParams3 = this.menuLl.getLayoutParams();
//		    localLayoutParams3.height = (523 * this.heightPx / 1240);
//		    localLayoutParams3.width = (685 * this.widthPx / 826);
//		    int i = 140 * (150 * this.widthPx / 826) / 150;
		    this.btn_night = ((Button)findViewById(R.id.btn_night));
		    this.btn_night.setOnClickListener(this);
//		    this.btn_night.getLayoutParams().height = i;
//		    this.btn_night.invalidate();
		    this.btn_meeting = ((Button)findViewById(R.id.btn_meeting));
		    this.btn_meeting.setOnClickListener(this);
//		    this.btn_meeting.getLayoutParams().height = i;
//		    this.btn_meeting.invalidate();
		    this.btn_reading = ((Button)findViewById(R.id.btn_reading));
		    this.btn_reading.setOnClickListener(this);
//		    this.btn_reading.getLayoutParams().height = i;
//		    this.btn_reading.invalidate();
		    this.btn_mode = ((Button)findViewById(R.id.btn_mode));
		    this.btn_mode.setOnClickListener(this);
//		    this.btn_mode.getLayoutParams().height = i;
//		    this.btn_mode.invalidate();
		    this.btn_timer = ((Button)findViewById(R.id.btn_timer));
		    this.btn_timer.setOnClickListener(this);
//		    this.btn_timer.getLayoutParams().height = i;
//		    this.btn_timer.invalidate();
		    this.btn_alarm = ((Button)findViewById(R.id.btn_alarm));
		    this.btn_alarm.setOnClickListener(this);
//		    this.btn_alarm.getLayoutParams().height = i;
//		    this.btn_alarm.invalidate();
		    this.btn_sleep = ((Button)findViewById(R.id.btn_sleep));
		    this.btn_sleep.setOnClickListener(this);
//		    this.btn_sleep.getLayoutParams().height = i;
//		    this.btn_sleep.invalidate();
		    this.btn_recreation = ((Button)findViewById(R.id.btn_recreation));
		    this.btn_recreation.setOnClickListener(this);
//		    this.btn_recreation.getLayoutParams().height = i;
//		    this.btn_recreation.invalidate();
//		    this.menuLl.invalidate();
		    this.btn_1 = ((TwoKeyButton)findViewById(R.id.btn_1));
		    this.btn_1.setOnClickListener(this);
		    this.btn_2 = ((TwoKeyButton)findViewById(R.id.btn_2));
		    this.btn_2.setOnClickListener(this);
		    this.btn_3 = ((TwoKeyButton)findViewById(R.id.btn_3));
		    this.btn_3.setOnClickListener(this);
		    this.btn_4 = ((TwoKeyButton)findViewById(R.id.btn_4));
		    this.btn_4.setOnClickListener(this);
//		    this.btn_1.setPadding(this.btn_1.getPaddingLeft(), this.btn_1.getPaddingTop(), this.btn_1.getPaddingRight(), 117 * this.heightPx / 1240);
//		    ViewGroup.LayoutParams localLayoutParams4 = this.btn_1.getLayoutParams();
//		    localLayoutParams4.height = (284 * this.heightPx / 1240);
//		    localLayoutParams4.width = (114 * this.widthPx / 826);
//		    this.btn_1.setLayoutParams(localLayoutParams4);
//		    this.btn_1.invalidate();
		    int j = (2 * this.btn_2.getPaddingLeft() + this.btn_3.getPaddingLeft()) / 3;
//		    this.btn_2.setPadding(j, this.btn_2.getPaddingTop(), this.btn_2.getPaddingRight(), 60 * this.heightPx / 1240);
//		    ViewGroup.LayoutParams localLayoutParams5 = this.btn_2.getLayoutParams();
//		    localLayoutParams5.height = (284 * this.heightPx / 1240);
//		    localLayoutParams5.width = (114 * this.widthPx / 826);
//		    this.btn_2.setLayoutParams(localLayoutParams5);
//		    this.btn_2.invalidate();
//		    ViewGroup.LayoutParams localLayoutParams6 = this.btn_3.getLayoutParams();
//		    localLayoutParams6.height = (284 * this.heightPx / 1240);
//		    localLayoutParams6.width = (114 * this.widthPx / 826);
//		    this.btn_3.setPadding(j, this.btn_3.getPaddingTop(), this.btn_3.getPaddingRight(), 60 * this.heightPx / 1240);
//		    this.btn_3.setLayoutParams(localLayoutParams6);
//		    this.btn_3.invalidate();
//		    ViewGroup.LayoutParams localLayoutParams7 = this.btn_4.getLayoutParams();
//		    localLayoutParams7.height = (284 * this.heightPx / 1240);
//		    localLayoutParams7.width = (114 * this.widthPx / 826);
//		    this.btn_4.setPadding(this.btn_4.getPaddingLeft(), this.btn_4.getPaddingBottom(), this.btn_4.getPaddingRight(), 117 * this.heightPx / 1240);
//		    this.btn_4.setLayoutParams(localLayoutParams7);
//		    this.btn_4.invalidate();
	}
	void log(String msg){
		Log.d(tag,msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_on:
			log("btn_on");
			mApp.SocketSend("btn_on".getBytes());
			break;
		case R.id.btn_off:
			log("btn_off");
			mApp.SocketSend("btn_off".getBytes());
			break;
		case R.id.btn_w:
			log("btn_w");
			mApp.SocketSend("btn_w".getBytes());
			break;
		case R.id.btn_home:
			log("btn_home");
			showSettingDialog();
			break;
		case R.id.btn_1:
			btn_1.toggle();
			log(""+btn_1.isStateOn());
			mApp.SocketSend(String.format("btn_1 %s",btn_1.isStateOn()).getBytes());
			break;
		case R.id.btn_2:
			btn_2.toggle();
			log(""+btn_2.isStateOn());
			mApp.SocketSend(String.format("btn_2 %s",btn_2.isStateOn()).getBytes());
			break;
		case R.id.btn_3:
			btn_3.toggle();
			log(""+btn_3.isStateOn());
			mApp.SocketSend(String.format("btn_3 %s",btn_3.isStateOn()).getBytes());
			break;
		case R.id.btn_4:
			btn_4.toggle();
			log(""+btn_4.isStateOn());
			mApp.SocketSend(String.format("btn_4 %s",btn_4.isStateOn()).getBytes());
			break;
		case R.id.btn_meeting:
			log("btn_meeting");
			mApp.SocketSend("btn_meeting".getBytes());
			break;
		case R.id.btn_alarm:
			log("btn_alarm");
			mApp.SocketSend("btn_alarm".getBytes());
			break;
		case R.id.btn_mode:
			log("btn_mode");
			mApp.SocketSend("btn_mode".getBytes());
			break;
		case R.id.btn_night:
			log("btn_night");
			mApp.SocketSend("btn_night".getBytes());
			break;
		case R.id.btn_reading:
			log("btn_reading");
			mApp.SocketSend("btn_reading".getBytes());
			break;
		case R.id.btn_recreation:
			log("btn_recreation");
			mApp.SocketSend("btn_recreation".getBytes());
			break;
		case R.id.btn_sleep:
			log("btn_sleep");
			mApp.SocketSend("btn_sleep".getBytes());
			break;
		case R.id.btn_timer:
			log("btn_timer");
			mApp.SocketSend("btn_timer".getBytes());
			break;
		default:
			break;
		}
	}
}
