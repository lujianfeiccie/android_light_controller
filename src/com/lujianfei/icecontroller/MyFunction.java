package com.lujianfei.icecontroller;

import com.lujianfei.icecontroller.ifunction.IFunction;

public class MyFunction implements IFunction {
	
	public MyFunction(){
		
	}
	public byte[] parseFunction(String functions){
		if(functions.equals(Common.MessageValueOfService.FUNCTION1_ON)){
			return setCheck1(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION1_OFF)){
			return setCheck1(false);
		}
		return null;
	}
	@Override
	public byte[] setCheck1(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[1];
		if(state){
			data[0] = 0x01;
		}else{
			data[0] = 0x02;	
		}
		return data;
	}

	@Override
	public byte[] setCheck2(boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] setCheck3(boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] setCheck4(boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] setCheck5(boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] setCheck6(boolean state) {
		// TODO Auto-generated method stub
		return null;
	}

}
