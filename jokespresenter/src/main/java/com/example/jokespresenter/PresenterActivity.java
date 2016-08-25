package com.example.jokespresenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PresenterActivity extends AppCompatActivity {

	public static final String TAG="joketag";
	private TextView jokeTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presenter);
		jokeTextView=(TextView)findViewById(R.id.jokesTextView);
		jokeTextView.setText(getIntent().getStringExtra(TAG));

	}
}
