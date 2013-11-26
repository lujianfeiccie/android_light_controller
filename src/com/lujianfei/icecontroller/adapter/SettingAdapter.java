package com.lujianfei.icecontroller.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.model.ConnectionInfo;

/*
 版权所有：版权所有(C)2013，固派软件
 文件名称：com.goopai.selfdrive.adapter.Tab1Adapter.java
 系统编号：
 系统名称：SelfDrive
 模块编号：
 模块名称：
 设计文档：
 创建日期：2013-11-21 下午3:53:48
 作 者：万海峰
 内容摘要：
 类中的代码包括三个区段：类变量区、类属性区、类方法区。
 文件调用:
 */
public class SettingAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ConnectionInfo> mData;

	public SettingAdapter(Context context, List<ConnectionInfo> _mData) {
		// TODO Auto-generated constructor stub
		this.mData = _mData;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;

		if (holder == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.setting_activity_item, null);

			holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
			holder.txt_ip = (TextView) convertView.findViewById(R.id.txt_ip);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_name.setText(mData.get(position).getName());
		holder.txt_ip.setText(mData.get(position).getAddr());

		return convertView;
	}
	public void refreshList(List<ConnectionInfo> mData) {
		this.mData = mData;
		this.notifyDataSetChanged();
	}
	public final class ViewHolder {
		public TextView txt_name;
		public TextView txt_ip;
	}

}
