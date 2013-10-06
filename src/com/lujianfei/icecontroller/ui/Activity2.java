package com.lujianfei.icecontroller.ui;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lujianfei.icecontroller.Protocol;
import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.custom.CircleImageView;
import com.lujianfei.icecontroller.ui.custom.CircleImageView.OnCircleImageViewChangeListener;
import com.lujianfei.icecontroller.ui.custom.RGBKeyButton;
import com.lujianfei.icecontroller.ui.custom.TwoKeyButton;
import com.lujianfei.icecontroller.ui.others.GlobalData;

public class Activity2 extends BaseActivity implements OnClickListener,OnCircleImageViewChangeListener
{
	private String tag = getClass().getSimpleName();
	  public DisplayMetrics displayMetrics;
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
	  Map<Byte,Byte> colorValue = new HashMap<Byte,Byte>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		log("onCreate");
		      setContentView(R.layout.activity2);
		    GlobalData.activity2 = this;
		     findView();
		    initData();
	}
	 private void initData() {
		// TODO Auto-generated method stub
		 colorValue.clear();
		 colorValue.put((byte)0x01, (byte)0x09);
		 colorValue.put((byte)0x02, (byte)0x08);
		 colorValue.put((byte)0x03, (byte)0x07);
		 colorValue.put((byte)0x04, (byte)0x06);
		 colorValue.put((byte)0x05, (byte)0x05);
		 colorValue.put((byte)0x06, (byte)0x04);
		 colorValue.put((byte)0x07, (byte)0x03);
		 colorValue.put((byte)0x08, (byte)0x02);
		 colorValue.put((byte)0x09, (byte)0x01);
		 colorValue.put((byte)0x0a, (byte)0x0c);
		 colorValue.put((byte)0x0b, (byte)0x0b);
		 colorValue.put((byte)0x0c, (byte)0x0a);
		 colorValue.put((byte)0x0d, (byte)0x0a);
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
		    this.circleImageView = ((CircleImageView)findViewById(R.id.circleImageView));
		    this.circleImageView.setOnCircleImageViewChange(this);
		    this.btn_rgb = ((RGBKeyButton)findViewById(R.id.btn_rgb));
		    this.btn_rgb.setOnClickListener(this);
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
		Log.d(tag, msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		byte[] data  =new byte[6];
			switch (v.getId()) {
			case R.id.btn_home:
				showSettingDialog();
				break;
			case R.id.btn_1:
				btn_1.toggle();
				log(""+btn_1.isStateOn());
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 1, btn_1.isStateOn());
				break;
			case R.id.btn_2:
				btn_2.toggle();
				log(""+btn_2.isStateOn());
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 2, btn_2.isStateOn());
				break;
			case R.id.btn_3:
				btn_3.toggle();
				log(""+btn_3.isStateOn());
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 3, btn_3.isStateOn());
				break;
			case R.id.btn_4:
				btn_4.toggle();
				log(""+btn_4.isStateOn());
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 4, btn_4.isStateOn());
				break;
			case R.id.btn_bright_down:
				log("btn_bright_down");
				mApp.control_bright_dark(Protocol.FLAG_UI_RGB,Protocol.FLAG_FUNCTION_BRIGHTDOWN);
				break;
			case R.id.btn_bright_up:
				log("btn_bright_up");
				mApp.control_bright_dark(Protocol.FLAG_UI_RGB,Protocol.FLAG_FUNCTION_BRIGHTUP);
				break;
			case R.id.btn_off:
				log("btn_off");
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 0, false);
				break;
			case R.id.btn_on:
				log("btn_on");
				mApp.control_toggle(Protocol.FLAG_UI_RGB, 0, true);
				break;
			case R.id.btn_rgb:
				btn_rgb.toggle();
				switch(btn_rgb.getMode()){
				case RGBKeyButton.MODE_RGB:
					log("MODE_RGB");
					//mApp.SocketSend("MODE_RGB".getBytes());
					break;
				case RGBKeyButton.MODE_R:
					log("MODE_R");
					//mApp.SocketSend("MODE_R".getBytes());
					mApp.control_rgb((byte)0x01);
					break;
				case RGBKeyButton.MODE_G:
					log("MODE_G");
					//mApp.SocketSend("MODE_G".getBytes());
					mApp.control_rgb((byte)0x05);
					break;
				case RGBKeyButton.MODE_B:
					log("MODE_B");
					//mApp.SocketSend("MODE_B".getBytes());
					mApp.control_rgb((byte)0x09);
					break;
				}
				break;
			default:
				break;
			}
			data = null;
	}
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
		if(btn_rgb.isRgbMode()){
		//	int[] data = paramCircleImageView.getRGB();
		//	mApp.SocketSend(String.format("RGB(%s %s %s)",data[0],data[1],data[2]).getBytes());
			byte temp = (byte) ((paramCircleImageView.getByteValue()&0xff)/(int)21.3 + 1);
			temp = colorValue.get(temp);
			mApp.control_rgb(temp);
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
