package com.fc2.web.hisai1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MenuFullscreenActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_fullscreen);
	}

	public void back(View v) {
		finish();
	}
}
