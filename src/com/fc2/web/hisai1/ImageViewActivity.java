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
	 * �A�N�e�B�r�e�B�쐬���ɌĂяo����郁�\�b�h
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// �X�[�p�[�N���X��onCreate���Ăяo��
		super.onCreate(savedInstanceState);
		
		// �R���e���g�r���[��"imageview.xml"���Z�b�g����
		setContentView(R.layout.imageview);
		
		// �C���e���g����l���擾����
		Intent intent = getIntent();
		int resourceID = intent.getIntExtra( "resourceID", 0);
		
		// �l���擾�ł��Ă���΁A���̒l������ImageView�ɉ摜���Z�b�g����
		if(resourceID != 0)
		{
			// ImageView���擾���āA�摜���Z�b�g����
			ImageView imageView = (ImageView)findViewById(R.id.imageView);
			imageView.setImageBitmap( BitmapFactory.decodeResource(getResources(), resourceID));
			
			// ImageView���^�b�`���ꂽ�Ƃ��ɌĂяo�����C�x���g���X�i�[�Ƃ��Ė����N���X���Z�b�g����
			imageView.setOnClickListener( new View.OnClickListener()
			{
				// ImageView���^�b�`�����Ƃ��ɌĂяo����郁�\�b�h
				@Override
				public void onClick(View view) {
					// ���̃A�N�e�B�r�e�B���I�����āA�O�̃A�N�e�B�r�e�B�ɖ߂�
					finish();
				}
			});
		}
	}
}
