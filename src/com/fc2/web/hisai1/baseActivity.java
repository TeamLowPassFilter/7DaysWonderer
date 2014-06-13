package com.fc2.web.hisai1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class baseActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseview);
		
		// 「TIPS」を押したら、TIPSアクティビティ（TipsListActivity）を起動。
		ImageView tips = (ImageView)findViewById(R.id.tips);
		tips.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(baseActivity.this, TipsListActivity.class);
				
				startActivity(intent);
			}
		});
	}
}
