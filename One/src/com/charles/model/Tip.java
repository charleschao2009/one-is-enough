package com.charles.model;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.charles.one.R;
import com.charles.util.Common;
import com.charles.util.MyDBFavHelper;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.keep.AccessTokenKeeper;
import com.weibo.sdk.android.net.RequestListener;
import com.weibo.sdk.android.sso.SsoHandler;
import com.weibo.sdk.android.util.Utility;

public class Tip extends Activity implements OnClickListener {

	
	private String fav_item;
	private Dialog mDialog;
	private TextView share_sina;
	private TextView share_qq;
	private TextView add_fav;
	private TextView cancel;
	private Weibo mWeibo;
	private Context context;
	private MyDBFavHelper dbHelper;
	public static Oauth2AccessToken accessToken;
	public static final String TAG = "sinasdk";

	/**
	 * SsoHandler 仅当sdk支持sso时有效，
	 */
	SsoHandler mSsoHandler;

	private final Handler tip_handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				dismiss();
			}
			
		}
		
		
		
	};
	
	public Tip(Context context, String fav_item) {
		this.context = context;
		this.fav_item = fav_item;
		dbHelper = new MyDBFavHelper(context,Common.DATABASE_NAME,Common.DATABASE_VERSION);
		mWeibo = Weibo.getInstance(Weibo.app_key, Weibo.redirecturl);
		initview(context);
	}

	public void initview(final Context context) {
		accessToken = AccessTokenKeeper.readAccessToken(context);
		if (accessToken.isSessionValid()) {
			Weibo.isWifi = Utility.isWifi(context);
			try {
				Class sso = Class.forName("com.weibo.sdk.android.api.WeiboAPI");// 如果支持weiboapi的话，显示api功能演示入口按钮
			} catch (ClassNotFoundException e) {
				Log.i(TAG, "com.weibo.sdk.android.api.WeiboAPI not found");
			}
		}
		mDialog = new Dialog(context, R.style.dialog);
		mDialog.setContentView(R.layout.tip);
		share_sina = (TextView) mDialog.findViewById(R.id.share_sina);
		share_qq = (TextView) mDialog.findViewById(R.id.share_qq);
		add_fav = (TextView) mDialog.findViewById(R.id.tip_add_fav);
		int count = isFav(dbHelper, fav_item);
		if (count > 0) {
			add_fav.setText("取消收藏(您已经收藏)");
		}
		cancel = (TextView) mDialog.findViewById(R.id.tip_cancel);
		share_sina.setOnClickListener(this);
		share_qq.setOnClickListener(this);
		add_fav.setOnClickListener(this);
		cancel.setOnClickListener(this);
		mDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0);
		Window window = mDialog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.setGravity(Gravity.CENTER);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = 0;
		wl.width = 500;
		wl.height = 400;
		window.setAttributes(wl);
	}

	public void show() {
		mDialog.show();
	}

	public void dismiss() {
		mDialog.dismiss();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.share_qq:
			break;
		case R.id.share_sina:
			mWeibo.authorize(context, new AuthDialogListener(fav_item));
			break;
		case R.id.tip_add_fav:
			/**
			 * 写到数据库
			 */
			if (add_fav.getText().equals("添加到收藏")) {
				dbHelper.insertAndUpdateData(dbHelper.getWritableDatabase(), 1111,
						fav_item);
				Toast.makeText(context, "添加成功" + fav_item, Toast.LENGTH_SHORT)
						.show();
			} else {
				dbHelper.deleteFromTable(dbHelper, fav_item);
				Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
			}
			dismiss();
			break;
		case R.id.tip_cancel:
			dismiss();
			break;
		}
	}

	/**
	 * 查看是否已收藏
	 */

	public int isFav(MyDBFavHelper myHelper, String title) {

		String sql = "select * from tmfav where title = '" + title + "'";
		SQLiteDatabase db = myHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery(sql, null);
		int count = cursor.getCount();

		return count;
	}

	

	class AuthDialogListener implements WeiboAuthListener {

		private String fav_item;

		public AuthDialogListener(String fav_item) {
			this.fav_item = fav_item;
		}

		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			Tip.accessToken = new Oauth2AccessToken(token, expires_in);
			if (Tip.accessToken.isSessionValid()) {
				try {
					Class sso = Class
							.forName("com.weibo.sdk.android.api.WeiboAPI");// 如果支持weiboapi的话，显示api功能演示入口按钮
				} catch (ClassNotFoundException e) {
					// e.printStackTrace();
					Log.i(TAG, "com.weibo.sdk.android.api.WeiboAPI not found");
				}
				AccessTokenKeeper.keepAccessToken(context, accessToken);
				Toast.makeText(context, "认证成功", Toast.LENGTH_SHORT).show();
				
				Message msg = new Message();
				msg.what = 1;
				tip_handler.sendMessage(msg);
				StatusesAPI api = new StatusesAPI(accessToken);
				api.update(fav_item, "90.0", "90.0", new RequestListener() {
					public void onIOException(IOException e) {
					}

					public void onError(WeiboException e) {
					}

					public void onComplete(String response) {

					}
				});
			}
		}

		public void onError(WeiboDialogError e) {
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
	}

}
