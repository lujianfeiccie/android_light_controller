package com.lujianfei.icecontroller.ui;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lujianfei.icecontroller.Protocol;
import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.handler.MyHandler;
import com.lujianfei.icecontroller.ui.custom.FourKeyButton;
import com.lujianfei.icecontroller.ui.custom.FourKeyButton.OnFourKeyTouchListener;
import com.lujianfei.icecontroller.ui.custom.TwoKeyButton;
import com.lujianfei.icecontroller.ui.others.GlobalData;
import com.lujianfei.icecontroller.ui.util.Util;

public class Activity1 extends BaseActivity 
implements OnClickListener,
					OnFourKeyTouchListener
{
	private String tag = getClass().getSimpleName();
	  public DisplayMetrics displayMetrics;
	  private Button btn_home;
	  private Button btn_off;
	  private Button btn_on;
	  private Button btn_w;
	  private TwoKeyButton btn_1;
	  private TwoKeyButton btn_2;
	  private TwoKeyButton btn_3;
	  private TwoKeyButton btn_4;
	  private RelativeLayout rlBtnFourRl;
	  private FourKeyButton btn_four;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		log("onCreate");
		  this.displayMetrics = new DisplayMetrics();
		      setContentView(R.layout.activity1);
		      findView();
		      initData();
	}
	 private void initData() {
		// TODO Auto-generated method stub
	}
	  private void findView()
	  {
	    Log.i("JXM", "Activity1 findViewing!!!");
	    this.btn_home = ((Button)findViewById(R.id.btn_home));
	    this.btn_home.setOnClickListener(this);
	    this.btn_on = ((Button)findViewById(R.id.btn_on));
	    this.btn_on.setOnClickListener(this);
	    this.btn_off = ((Button)findViewById(R.id.btn_off));
	    this.btn_off.setOnClickListener(this);
	    this.rlBtnFourRl = ((RelativeLayout)findViewById(R.id.fourKeyView_rl));
	    this.btn_four = ((FourKeyButton)findViewById(R.id.fourKeyView));
	    this.btn_four.setOnFourKeyTouchListener(this);
	    this.btn_w = ((Button)findViewById(R.id.btn_w));
	    this.btn_w.setOnClickListener(this);
	    this.btn_w.invalidate();
	    this.btn_1 =((TwoKeyButton)findViewById(R.id.btn_1));
	    this.btn_1.setOnClickListener(this);
	    this.btn_2 = ((TwoKeyButton)findViewById(R.id.btn_2));
	    this.btn_2.setOnClickListener(this);
	    this.btn_3 = ((TwoKeyButton)findViewById(R.id.btn_3));
	    this.btn_3.setOnClickListener(this);
	    this.btn_4 = ((TwoKeyButton)findViewById(R.id.btn_4));
	    this.btn_4.setOnClickListener(this);
	  }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		byte[] data  =new byte[6];
		switch (v.getId()) {
		case R.id.btn_off:
			log("btn_off");
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 0, false);
			break;
		case R.id.btn_on:
			log("btn_on");
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 0, true);
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
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 1, btn_1.isStateOn());
			break;
		case R.id.btn_2:
			btn_2.toggle();
			log(""+btn_2.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 2, btn_2.isStateOn());
			break;
		case R.id.btn_3:
			btn_3.toggle();
			log(""+btn_3.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 3, btn_3.isStateOn());
			break;
		case R.id.btn_4:
			btn_4.toggle();
			log(""+btn_4.isStateOn());
			mApp.control_toggle(Protocol.FLAG_UI_COLOR, 4, btn_4.isStateOn());
			break;
		default:
			break;
		}
		data = null;
	}
	@Override
	public void onKeyTouchEnd(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onKeyTouchEnd");
	}
	@Override
	public void onDownKeyTouchDown(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onDownKeyTouchDown");
		//调暗开始
		mApp.control_cool_or_warm_down(Protocol.FLAG_FUNCTION_COOL_WARM_INCREASE);
	}
	@Override
	public void onLeftKeyTouchDown(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onLeftKeyTouchDown");
	}
	@Override
	public void onRightKeyTouchDown(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onRightKeyTouchDown");
	}
	@Override
	public void onUpKeyTouchDown(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onUpKeyTouchDown");
		//调暗开始
		mApp.control_cool_or_warm_up(Protocol.FLAG_FUNCTION_COOL_WARM_INCREASE);
	}
	@Override
	public void onDownKeyTouchUp(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onDownKeyTouchUp");
		//调暗结束
		mApp.control_cool_or_warm_down(Protocol.FLAG_FUNCTION_COOL_WARM_STOP);
	}
	@Override
	public void onLeftKeyTouchUp(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onLeftKeyTouchUp");
		//选择冷色
		mApp.setCool(true);
	}
	@Override
	public void onRightKeyTouchUp(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onRightKeyTouchUp");
		//选择暖色
		mApp.setCool(false);
	}
	@Override
	public void onUpKeyTouchUp(FourKeyButton paramFourKeyButton) {
		// TODO Auto-generated method stub
		log("onUpKeyTouchUp");
		//调暗结束
		mApp.control_cool_or_warm_up(Protocol.FLAG_FUNCTION_COOL_WARM_STOP);
	}
	void log(String msg){
		Log.d(tag, msg);
	}
}
