package com.atneik.ttt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryActivity extends Activity {
	
	View v;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
		
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(getApplicationContext(), "" + position , Toast.LENGTH_SHORT).show();
	            Log.d("image", v.toString());
	            v.animate().scaleXBy(position);
	            TtT.setCategory("Hello");
	            updateTextView();
	            callMoreInfo(v);
	        }
	    });
	}
	@Override
    public void onResume() {
        super.onResume();
        updateTextView();
        
	}
	
	@Override
    public void onBackPressed()
    {
		Intent intent = new Intent(this, LocationActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
        
	    super.onBackPressed(); 
    }
	
	public void callMoreInfo(View view) {
		Intent intent = new Intent(this, MoreInfoActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
	    finish();
	}
	
	private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());
	}
}