package com.weibo.sdk.android.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.charles.one.R;
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

public class MainActivity extends Activity {
	private Weibo mWeibo;
	private static final String CONSUMER_KEY = "3326522848";// 替换为开发者的appkey，例如"1646212860";
	private static final String REDIRECT_URL = "http://www.weibo.com";
	private Button authBtn;
	public static Oauth2AccessToken accessToken;
	public static final String TAG = "sinasdk";
	/**
	 * SsoHandler 仅当sdk支持sso时有效，
	 */
	SsoHandler mSsoHandler;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.test);
		mWeibo = Weibo.getInstance(CONSUMER_KEY, REDIRECT_URL);
		authBtn = (Button) findViewById(R.id.auth);
		authBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mWeibo.authorize(MainActivity.this, new AuthDialogListener());
			}
		});

		MainActivity.accessToken = AccessTokenKeeper.readAccessToken(this);
		if (MainActivity.accessToken.isSessionValid()) {
			Weibo.isWifi = Utility.isWifi(this);
			try {
				Class sso = Class.forName("com.weibo.sdk.android.api.WeiboAPI");// 如果支持weiboapi的话，显示api功能演示入口按钮
			} catch (ClassNotFoundException e) {
				Log.i(TAG, "com.weibo.sdk.android.api.WeiboAPI not found");
			}
		}
	}

	class AuthDialogListener implements WeiboAuthListener {

		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			MainActivity.accessToken = new Oauth2AccessToken(token, expires_in);
			if (MainActivity.accessToken.isSessionValid()) {
				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(new java.util.Date(MainActivity.accessToken
								.getExpiresTime()));
				try {
					Class sso = Class
							.forName("com.weibo.sdk.android.api.WeiboAPI");// 如果支持weiboapi的话，显示api功能演示入口按钮
				} catch (ClassNotFoundException e) {
					// e.printStackTrace();
					Log.i(TAG, "com.weibo.sdk.android.api.WeiboAPI not found");
				}
				AccessTokenKeeper.keepAccessToken(MainActivity.this,
						accessToken);
				Toast.makeText(MainActivity.this, "认证成功", Toast.LENGTH_SHORT)
						.show();
				StatusesAPI api = new StatusesAPI(accessToken);
				api.update("大功告成", "90.0", "90.0",
						new RequestListener() {
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
