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

public class ListViewActivity extends Activity 
{
	/**
	 * ListView�ɕ\������v�f�̃N���X
	 */
	private class ListViewItem
	{
		private int resourceID;
		private String fileName;
		private Bitmap img;
		
		/**
		 * �R���X�g���N�^
		 * @param resource_id �摜�t�@�C���̃��\�[�XID�l
		 * @param file_name �摜�t�@�C���̃t�@�C����
		 * @param img �摜�t�@�C����ϊ����č쐬�����r�b�g�}�b�v
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
	 * ListView�ɃZ�b�g����A�_�v�^�̃N���X
	 */
	private class ListViewItemAdapter extends ArrayAdapter<ListViewItem>
	{
		private LayoutInflater layoutInflater;
		
		/**
		 * �R���X�g���N�^
		 */
		public ListViewItemAdapter(Context context, int textViewResourceId,
				List<ListViewItem> objects)
		{
			// �X�[�p�[�N���X�̃R���X�g���N�^���Ăяo��
			super(context, textViewResourceId, objects);
			
			// LayoutInflater���擾����
			layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		}
		
		/**
		 * ListView�̊e�s���\������v�f��Ԃ����\�b�h
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// convertView��null�������ꍇ�̂݁ALayoutInflater�𗘗p���āA"listview_item.xml"����r���[���擾����
			if(convertView == null)
			{
				convertView = layoutInflater.inflate(R.layout.listview_item, null);
			}
			
			// position�s�ڂ̃f�[�^���擾����
			ListViewItem item = (ListViewItem)getItem(position);
			
			// ImageView�ɉ摜���Z�b�g����
			ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap( item.getImage() );
			
			//TextView�ɕ�������Z�b�g����
			TextView textView = (TextView)convertView.findViewById(R.id.textView_Item);
			textView.setText( item.getFileName() );
			
			// convertView��Ԃ�
			return convertView;
		}
	}
	
	/**
	 * �A�N�e�B�r�e�B�쐬���ɌĂяo����郁�\�b�h
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// �X�[�p�[�N���X��onCreate���Ăяo��
		super.onCreate(savedInstanceState);
		
		// �R���e���g�r���[��"listview.xml"���Z�b�g����
		setContentView(R.layout.listview);
		
		// ListView�ɕ\������v�f���쐬����
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
		
		// �A�_�v�^�Ƀ��X�g���Z�b�g����
		ListViewItemAdapter adapter = new ListViewItemAdapter(this, 0, list);
		
		// ListView���擾����
		ListView listView = (ListView)findViewById(R.id.listView);
		
		//ListView�ɃA�_�v�^���Z�b�g����
		listView.setAdapter(adapter);
		
		// ListView�̗v�f���^�b�`���ꂽ�Ƃ��ɌĂяo�����C�x���g���X�i�[�Ƃ��Ė����N���X���Z�b�g����
		listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
		{
			// �v�f���^�b�`�����Ƃ��ɌĂяo����郁�\�b�h
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				// �I�������v�f���擾����
				ListView listView = (ListView)parent;
				ListViewItem item = (ListViewItem)listView.getItemAtPosition(position);
				
				// ImageViewActivity�ɑJ�ڂ��邽�߂̃C���e���g���쐬����
				Intent intent = new Intent(ListViewActivity.this, ImageViewActivity.class);
				
				// �C���e���g�ɁA�I�������v�f��resourceID�l���Z�b�g����
				intent.putExtra( "resourceID", item.getResourceID());
				
				// ImageViewActivity�ւƑJ�ڂ���
				startActivity(intent);
			}
		});
	}
}
