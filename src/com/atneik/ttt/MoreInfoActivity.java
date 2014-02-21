package com.atneik.ttt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MoreInfoActivity extends Activity {
	
	EditText mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moreinfo);
		
		mEdit = (EditText)findViewById(R.id.editText1);
	}
	
	@Override
    public void onResume() {
        super.onResume();
        updateTextView();
        
	}	
	Handler mTweetHandler = new Handler();
    Runnable mTweetRunnable = new Runnable() {
        @Override
        public void run() {
            String url = null;
            
            try {
                url = "http://www.twitter.com/intent/tweet?url=" + URLEncoder.encode(TtT.getTweetText(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            try{
                Log.d("url",url);
                i.setDataAndNormalize(Uri.parse(url));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            TtT.reset();
            startActivity(i);
            //startActivityForResult(i, 1);
            finish();
            
        }
    };
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
          super.onActivityResult(requestCode, resultCode, data);

           Log.d("code",Integer.toString(resultCode));
    }
    

	@Override
    public void onBackPressed()
    {
		Intent intent = new Intent(this, CatMoreActivity.class);
	    startActivity(intent);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	    super.onBackPressed(); 
    }
    	
    public void callTweetIntent(View v) {
    	TtT.setMoreInfo(mEdit.getText().toString());
        mTweetHandler.removeCallbacks(mTweetRunnable);
        mTweetHandler.post(mTweetRunnable);
    }
    private void updateTextView(){
		 TextView tweetTextView = (TextView) findViewById(R.id.tweetText);
	     tweetTextView.setText(TtT.getTweetText());
	     
	     TextView tweetCount = (TextView) findViewById(R.id.tweetCount);
	     tweetCount.setText(TtT.getTweetLeft().toString());
	}
}