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

public class DisasterActivity extends Activity {
	
	private String[] name ={"haiti","seattle","delhi","tokyo","nyc"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disaster);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        
	    	GridView gridview = (GridView) findViewById(R.id.gridview);
			
		    gridview.setAdapter(new ImageAdapter(this));

		    gridview.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		            //Toast.makeText(getApplicationContext(), "" + position + v.toString() , Toast.LENGTH_SHORT).show();
		            //Log.d("image", position + Resources.getSystem().getResourcePackageName((int) id) +"--"+ Resources.getSystem().getString((int)id));
		        	Log.d("image", position +"--"+Double.toString(id));
		            TtT.setDisaster(name[position]);
		            //v.animate().scaleXBy(position);
		            callLocation(v);
		            
		        }
		    });
		    updateTextView();
    }
		
    public void callLocation(View view) {
		Intent intent = new Intent(this, LocationActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
	    finish();
	}
    private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());

	     TextView tweetCount = (TextView) findViewById(R.id.tweetCount);
	     //Log.d("count", TtT.getTweetLeft());
	     tweetCount.setText(TtT.getTweetLeft().toString());
	}
}