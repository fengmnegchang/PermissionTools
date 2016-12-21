package com.example.settingsprj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FloatActivity extends Activity{
	RelativeLayout floatlayout;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_float);
		String content = getIntent().getStringExtra("content");
		
		floatlayout = (RelativeLayout) findViewById(R.id.floatlayout);
		floatlayout.setBackgroundColor(getResources().getColor(R.color.half_transparent));
		((TextView)findViewById(R.id.floattxt)).setText(content);
		findViewById(R.id.floatlayout).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
