package com.atneik.ttt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CatMoreActivity extends Activity {
	
	EditText mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catmore);
		
		mEdit = (EditText)findViewById(R.id.editText1);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        updateTextView();
        
	}	
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
          super.onActivityResult(requestCode, resultCode, data);

           Log.d("code",Integer.toString(resultCode));
    }
    
    public void callCatMore(View v){
    	TtT.setCatMore(mEdit.getText().toString());
    	Intent intent = new Intent(this, MoreInfoActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    startActivity(intent);
	    finish();
    }

	@Override
    public void onBackPressed()
    {
		Intent intent = new Intent(this, CategoryActivity.class);
	    startActivity(intent);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    super.onBackPressed(); 
    }
    	
    private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());
	     
	     TextView tweetCount = (TextView) findViewById(R.id.tweetCount);
	     tweetCount.setText(TtT.getTweetLeft().toString());
	}
}