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

public class CategoryActivity extends Activity {
	
	private String[] name ={"need","offer","shelter","damage","power"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new CatImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            
	        	Log.d("image", v.toString());
	            TtT.setCategory(name[position]);
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
		Intent intent = new Intent(this, CatMoreActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
	    finish();
	}
	
	private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());
	     
	     TextView tweetCount = (TextView) findViewById(R.id.tweetCount);
	     tweetCount.setText(TtT.getTweetLeft().toString());
	}
}