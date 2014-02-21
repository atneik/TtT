package com.atneik.ttt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LocationActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        updateTextView();
	}
	
	@Override
    public void onBackPressed()
    {
		Intent intent = new Intent(this, DisasterActivity.class);
	    startActivity(intent);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    super.onBackPressed(); 
    }
	
	public void callCategory(View view) {
		Intent intent = new Intent(this, CategoryActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
	    finish();
	}
	private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());
	}
}