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
		
		else if(functions.equals(Common.MessageValueOfService.FUNCTION2_ON)){
			return setCheck2(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION2_OFF)){
			return setCheck2(false);
		}
		
		else if(functions.equals(Common.MessageValueOfService.FUNCTION3_ON)){
			return setCheck3(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION3_OFF)){
			return setCheck3(false);
		}
		
		else if(functions.equals(Common.MessageValueOfService.FUNCTION4_ON)){
			return setCheck4(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION4_OFF)){
			return setCheck4(false);
		}
		
		else if(functions.equals(Common.MessageValueOfService.FUNCTION5_ON)){
			return setCheck5(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION5_OFF)){
			return setCheck5(false);
		}
		
		
		else if(functions.equals(Common.MessageValueOfService.FUNCTION6_ON)){
			return setCheck6(true);
		}else if(functions.equals(Common.MessageValueOfService.FUNCTION6_OFF)){
			return setCheck6(false);
		}
		return null;
	}
	@Override
	public byte[] setCheck1(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x01;
			data[1] = 0x01;
		}else{
			data[0] = 0x01;
			data[1] = 0x00;
		}
		return data;
	}

	@Override
	public byte[] setCheck2(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x02;
			data[1] = 0x01;
		}else{
			data[0] = 0x02;
			data[1] = 0x00;
		}
		return data;
	}

	@Override
	public byte[] setCheck3(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x03;
			data[1] = 0x01;
		}else{
			data[0] = 0x03;
			data[1] = 0x00;
		}
		return data;
	}

	@Override
	public byte[] setCheck4(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x04;
			data[1] = 0x01;
		}else{
			data[0] = 0x04;
			data[1] = 0x00;
		}
		return data;
	}

	@Override
	public byte[] setCheck5(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x05;
			data[1] = 0x01;
		}else{
			data[0] = 0x05;
			data[1] = 0x00;
		}
		return data;
	}

	@Override
	public byte[] setCheck6(boolean state) {
		// TODO Auto-generated method stub
		byte[] data = new byte[2];
		if(state){
			data[0] = 0x06;
			data[1] = 0x01;
		}else{
			data[0] = 0x06;
			data[1] = 0x00;
		}
		return data;
	}

}
