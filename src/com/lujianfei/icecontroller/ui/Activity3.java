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

import com.lujianfei.icecontroller.Protocol;
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
		    this.menuLl = ((LinearLayout)findViewById(R.id.menu_ll));
		    this.btn_night = ((Button)findViewById(R.id.btn_night));
		    this.btn_night.setOnClickListener(this);
		    this.btn_meeting = ((Button)findViewById(R.id.btn_meeting));
		    this.btn_meeting.setOnClickListener(this);
		    this.btn_reading = ((Button)findViewById(R.id.btn_reading));
		    this.btn_reading.setOnClickListener(this);
		    this.btn_mode = ((Button)findViewById(R.id.btn_mode));
		    this.btn_mode.setOnClickListener(this);
		    this.btn_timer = ((Button)findViewById(R.id.btn_timer));
		    this.btn_timer.setOnClickListener(this);
		    this.btn_alarm = ((Button)findViewById(R.id.btn_alarm));
		    this.btn_alarm.setOnClickListener(this);
		    this.btn_sleep = ((Button)findViewById(R.id.btn_sleep));
		    this.btn_sleep.setOnClickListener(this);
		    this.btn_recreation = ((Button)findViewById(R.id.btn_recreation));
		    this.btn_recreation.setOnClickListener(this);
		    this.btn_1 = ((TwoKeyButton)findViewById(R.id.btn_1));
		    this.btn_1.setOnClickListener(this);
		    this.btn_2 = ((TwoKeyButton)findViewById(R.id.btn_2));
		    this.btn_2.setOnClickListener(this);
		    this.btn_3 = ((TwoKeyButton)findViewById(R.id.btn_3));
		    this.btn_3.setOnClickListener(this);
		    this.btn_4 = ((TwoKeyButton)findViewById(R.id.btn_4));
		    this.btn_4.setOnClickListener(this);
	}
	void log(String msg){
		Log.d(tag,msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_off:
			log("btn_off");
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 0, false);
			break;
		case R.id.btn_on:
			log("btn_on");
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 0, true);
			break;
		case R.id.btn_w:
			log("btn_w");
			//mApp.SocketSend("btn_w".getBytes());
			break;
		case R.id.btn_home:
			log("btn_home");
			showSettingDialog();
			break;
		case R.id.btn_1:
			btn_1.toggle();
			log(""+btn_1.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 1, btn_1.isStateOn());
			break;
		case R.id.btn_2:
			btn_2.toggle();
			log(""+btn_2.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 2, btn_2.isStateOn());
			break;
		case R.id.btn_3:
			btn_3.toggle();
			log(""+btn_3.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 3, btn_3.isStateOn());
			break;
		case R.id.btn_4:
			btn_4.toggle();
			log(""+btn_4.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_MODE, 4, btn_4.isStateOn());
			break;
		case R.id.btn_meeting:
			log("btn_meeting");
			mApp.control_mode(Protocol.FLAG_FUNCTION_MEETING);
			break;
		case R.id.btn_alarm:
			log("btn_alarm");
			mApp.control_mode(Protocol.FLAG_FUNCTION_ALARM);
			break;
		case R.id.btn_mode:
			log("btn_mode");
			mApp.control_mode(Protocol.FLAG_FUNCTION_MODE);
			break;
		case R.id.btn_night:
			log("btn_night");
			mApp.control_mode(Protocol.FLAG_FUNCTION_NIGHT);
			break;
		case R.id.btn_reading:
			log("btn_reading");
			mApp.control_mode(Protocol.FLAG_FUNCTION_READING);
			break;
		case R.id.btn_recreation:
			log("btn_recreation");
			mApp.control_mode(Protocol.FLAG_FUNCTION_RECREATION);
			break;
		case R.id.btn_sleep:
			log("btn_sleep");
			mApp.control_mode(Protocol.FLAG_FUNCTION_SLEEP);
			break;
		case R.id.btn_timer:
			log("btn_timer");
			mApp.control_mode(Protocol.FLAG_FUNCTION_TIMER);
			break;
		default:
			break;
		}
	}
}
