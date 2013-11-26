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
 ��Ȩ���У���Ȩ����(C)2013���������
 �ļ����ƣ�com.goopai.selfdrive.adapter.Tab1Adapter.java
 ϵͳ��ţ�
 ϵͳ���ƣ�SelfDrive
 ģ���ţ�
 ģ�����ƣ�
 ����ĵ���
 �������ڣ�2013-11-21 ����3:53:48
 �� �ߣ��򺣷�
 ����ժҪ��
 ���еĴ�������������Σ���������������������෽������
 �ļ�����:
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
