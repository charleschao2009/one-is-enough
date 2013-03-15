package com.charles.model;

import com.charles.one.R;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.keep.AccessTokenKeeper;
import com.weibo.sdk.android.util.Utility;

import android.app.Dialog;
import android.content.Context;
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

public class FavTip implements OnClickListener{

	private Handler handler;
	private int position;
	private Context context;
	private Dialog FTDialog;
	private TextView favTipDel;
	private TextView favTipCancel;
	public FavTip(Context context,Handler handler,int position){
		this.handler = handler;
		this.position = position;
		this.context = context;
		initview(context);
	}
	public void initview(final Context context) {
		FTDialog = new Dialog(context, R.style.dialog);
		FTDialog.setContentView(R.layout.fav_tip);
		favTipDel = (TextView) FTDialog.findViewById(R.id.fav_tip_del);
		favTipCancel = (TextView) FTDialog.findViewById(R.id.fav_tip_cancel);
		favTipDel.setOnClickListener(this);
		favTipCancel.setOnClickListener(this);
		FTDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0);
		Window window = FTDialog.getWindow();
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
		FTDialog.show();
	}

	public void dismiss() {
		FTDialog.dismiss();
	}
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.fav_tip_del:
			Message msg = new Message();
			msg.arg1 = 1;
			msg.arg2  =position;
			handler.sendMessage(msg);
			dismiss();
			Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
			break;
		case R.id.fav_tip_cancel:
			dismiss();
		}
	}
}
