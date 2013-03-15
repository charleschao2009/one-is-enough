package com.charles.one;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Share extends Activity {
	private Button btn_share;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		btn_share = (Button) findViewById(R.id.btn_fenxiang);
		btn_share.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
				intent.setType("text/plain"); // 分享发送的数据类型
				intent.putExtra(Intent.EXTRA_SUBJECT, "subject"); // 分享的主题
				intent.putExtra(Intent.EXTRA_TEXT, "extratext"); // 分享的内容
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 这个也许是分享列表的背景吧
				Share.this.startActivity(Intent.createChooser(intent, "分享"));// 目标应用选择对话框的标
			}
		});
	}
}
