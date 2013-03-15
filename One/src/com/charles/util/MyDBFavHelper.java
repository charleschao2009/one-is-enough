package com.charles.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBFavHelper extends SQLiteOpenHelper {

	public MyDBFavHelper(Context context, String DATABASE_NAME,
			int DATABASE_VERSION) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table tmfav("
				+ "id integer primary key autoincrement,contentId integer,title varchar(255))");
	}

	// 当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("create table tmfav("
				+ "id integer primary key autoincrement,contentId integer,title varchar(255))");
	}

	// 向数据库中插入和更新数据
	public void insertAndUpdateData(SQLiteDatabase db, int contentId,
			String title) {
		// 获取数据库对象
		// 使用execSQL方法向表中插入数据
		db.execSQL("insert into tmfav(contentId,title) values(" + contentId
				+ ",'" + title + "')");
		db.close();
	}

	// 从数据库中删除数据
	public void deleteFromTable(MyDBFavHelper myHelper, String title) {
		String sql = "delete from tmfav where title = '" + title + "'";
		SQLiteDatabase db = myHelper.getReadableDatabase();
		db.execSQL(sql);
	}

	public List<Map<String, String>>  queryData(MyDBFavHelper myHelper) {
		// 获得数据库对象
		SQLiteDatabase db = myHelper.getReadableDatabase();
		// 查询表中的数据
		Cursor cursor = db.query("tmfav", null, null, null, null, null,
				"id desc");
		// 获取name列的索引
		int urlIndex = cursor.getColumnIndex("contentId");
		// 获取level列的索引
		int introIndex = cursor.getColumnIndex("title");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("contentId", cursor.getString(urlIndex));
			map.put("title", cursor.getString(introIndex));
			list.add(map);
		}
		cursor.close();// 关闭结果集
		db.close();// 关闭数据库对象
		return list;
	}
}
