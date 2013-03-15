package com.charles.util;

import java.util.List;
import java.util.Map;

import com.charles.model.FavTip;
import com.charles.one.R;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavAdapter extends BaseAdapter {

	
	private LayoutInflater inflater;
	private List<Map<String, String>> list;
	private Context context;
	private Handler handler;
	public FavAdapter(Context context, List<Map<String, String>> list,Handler handler) {
		this.context = context;
		this.list = list;
		this.handler = handler;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int location) {
		return list.get(location);
	}

	public long getItemId(int location) {
		return location;
	}

	public View getView(final int position, View convertView, ViewGroup viewGroup) {
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.fav_items,null);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.position = position;
		final Map<String,String> map = list.get(position);
		
		viewHolder.fav_item_image = (ImageView)convertView.findViewById(R.id.fav_item_image);
		viewHolder.fav_item_text = (TextView)convertView.findViewById(R.id.fav_item_text);
		viewHolder.fav_item_text.setText(map.get("title"));
		
		convertView.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				new FavTip(context,handler,position).show();
				return false;
			}
		});
		return convertView;
	}
	
	
	class ViewHolder{
		private TextView fav_item_text;
		private ImageView fav_item_image;
		private int position;
	}
}
