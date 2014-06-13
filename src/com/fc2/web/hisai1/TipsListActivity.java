package com.fc2.web.hisai1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TipsListActivity extends Activity 
{
	/**
	 * ListViewに表示する要素のクラス
	 */
	private class ListViewItem
	{
		private int resourceID;
		private String fileName;
		private Bitmap img;
		
		/**
		 * コンストラクタ
		 * @param resource_id 画像ファイルのリソースID値
		 * @param file_name 画像ファイルのファイル名
		 * @param img 画像ファイルを変換して作成したビットマップ
		 */
		public ListViewItem(int resource_id, String file_name, Bitmap img)
		{
			this.resourceID = resource_id;
			this.fileName = file_name;
			this.img = img;
		}
		
		// Getter
		public int getResourceID() { return resourceID; }
		public String getFileName() { return fileName; }
		public Bitmap getImage() { return img; }
	}
	
	/**
	 * ListViewにセットするアダプタのクラス
	 */
	private class ListViewItemAdapter extends ArrayAdapter<ListViewItem>
	{
		private LayoutInflater layoutInflater;
		
		/**
		 * コンストラクタ
		 */
		public ListViewItemAdapter(Context context, int textViewResourceId,
				List<ListViewItem> objects)
		{
			// スーパークラスのコンストラクタを呼び出す
			super(context, textViewResourceId, objects);
			
			// LayoutInflaterを取得する
			layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		}
		
		/**
		 * ListViewの各行が表示する要素を返すメソッド
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// convertViewがnullだった場合のみ、LayoutInflaterを利用して、"listview_item.xml"からビューを取得する
			if(convertView == null)
			{
				convertView = layoutInflater.inflate(R.layout.listview_item, null);
			}
			
			// position行目のデータを取得する
			ListViewItem item = (ListViewItem)getItem(position);
			
			// ImageViewに画像をセットする
			ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap( item.getImage() );
			
			//TextViewに文字列をセットする
			TextView textView = (TextView)convertView.findViewById(R.id.textView_Item);
			textView.setText( item.getFileName() );
			
			// convertViewを返す
			return convertView;
		}
	}
	
	/**
	 * アクティビティ作成時に呼び出されるメソッド
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// スーパークラスのonCreateを呼び出す
		super.onCreate(savedInstanceState);
		
		// コンテントビューに"listview.xml"をセットする
		setContentView(R.layout.listview);
		
		// ListViewに表示する要素を作成する
		List<ListViewItem> list = new ArrayList<ListViewItem>();
		list.add( new ListViewItem(
				R.drawable.akamatsu, "akamatsu.jpg",
				BitmapFactory.decodeResource(getResources(), R.drawable.akamatsu) ));
		list.add( new ListViewItem(
				R.drawable.sakura, "sakura.jpg",
				BitmapFactory.decodeResource(getResources(), R.drawable.sakura) ));
		list.add( new ListViewItem(
				R.drawable.tsukushi, "tsukushi.jpg",
				BitmapFactory.decodeResource(getResources(), R.drawable.tsukushi)));
		
		// アダプタにリストをセットする
		ListViewItemAdapter adapter = new ListViewItemAdapter(this, 0, list);
		
		// ListViewを取得する
		ListView listView = (ListView)findViewById(R.id.listView);
		
		//ListViewにアダプタをセットする
		listView.setAdapter(adapter);
		
		// ListViewの要素がタッチされたときに呼び出されるイベントリスナーとして無名クラスをセットする
		listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
		{
			// 要素をタッチしたときに呼び出されるメソッド
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// 選択した要素を取得する
				ListView listView = (ListView)parent;
				ListViewItem item = (ListViewItem)listView.getItemAtPosition(position);
				
				// ImageViewActivityに遷移するためのインテントを作成する
				Intent intent = new Intent(TipsListActivity.this, ImageViewActivity.class);
				
				// インテントに、選択した要素のresourceID値をセットする
				intent.putExtra( "resourceID", item.getResourceID());
				
				// ImageViewActivityへと遷移する
				startActivity(intent);
			}
		});
		
		// 「BACK」を押したらアクティビティを終了させる
		TextView backText = (TextView)findViewById(R.id.backText);
		backText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
}
