package com.fc2.web.hisai1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends Activity
{
	/**
	 * アクティビティ作成時に呼び出されるメソッド
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// スーパークラスのonCreateを呼び出す
		super.onCreate(savedInstanceState);
		
		// コンテントビューに"imageview.xml"をセットする
		setContentView(R.layout.imageview);
		
		// インテントから値を取得する
		Intent intent = getIntent();
		int resourceID = intent.getIntExtra( "resourceID", 0);
		
		// 値が取得できていれば、その値を元にImageViewに画像をセットする
		if(resourceID != 0)
		{
			// ImageViewを取得して、画像をセットする
			ImageView imageView = (ImageView)findViewById(R.id.imageView);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), resourceID));
			
			// ImageViewをタッチされたときに呼び出されるイベントリスナーとして無名クラスをセットする
			imageView.setOnClickListener( new View.OnClickListener()
			{
				// ImageViewをタッチしたときに呼び出されるメソッド
				@Override
				public void onClick(View view) {
					// このアクティビティを終了して、前のアクティビティに戻る
					finish();
				}
			});
		}
	}
}
