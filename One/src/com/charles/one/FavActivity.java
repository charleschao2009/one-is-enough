package com.charles.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.TextView;

import com.charles.util.Common;
import com.charles.util.FavAdapter;
import com.charles.util.MyDBFavHelper;

public class FavActivity extends Activity {

	private ListView fav_list;
	private MyDBFavHelper dbHelper;
	private List<Map<String,String>> list;
	
	private FavAdapter favAdapter;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			int temp = (Integer) msg.arg1;
			if(temp==1){
				String title = list.get(msg.arg2).get("title");
				
				list.remove(msg.arg2);
				favAdapter.notifyDataSetChanged();
				
				/**
				 * 同时删除sharepreference数据
				 */
				dbHelper.deleteFromTable(dbHelper, title);
			}
		};
	};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fav);
		initview();
		dbHelper = new MyDBFavHelper(FavActivity.this,Common.DATABASE_NAME,Common.DATABASE_VERSION);
		list = dbHelper.queryData(dbHelper);
		favAdapter = new FavAdapter(this,list,handler);
		fav_list.setAdapter(favAdapter);
	}
	public void initview(){
		fav_list = (ListView) findViewById(R.id.fav_list);
	}
	
	
	
	/**
	 * 删除该条记录
	 */
	public void delete(MyDBFavHelper myHelper){
		// 获得数据库对象
				SQLiteDatabase db = myHelper.getReadableDatabase();
	}
	
	
}
