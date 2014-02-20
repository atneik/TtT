package com.atneik.ttt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.SupportMapFragment;

public class SectionFragment extends Fragment 
implements
OnMyLocationButtonClickListener{
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	private GoogleMap mMap;
	
	
	public SectionFragment() {
	}
	
	@Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
	
	private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
        	FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
			
            mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
            }
        }
    }
	
	public void setLocation(String loc){
		location = loc;
		updateTweet();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		TextView tweetTextView = (TextView) rootView
				.findViewById(R.id.tweetText);
		TextView tweetCount = (TextView) rootView
				.findViewById(R.id.tweetCount);
		
		updateTweet();
		
		if(tweetText != null){
			tweetTextView.setText(tweetText);
			tweetText = tweetTextView.getText().toString();
			Integer countLeft = 140-tweetText.length();
			Log.d("count", Integer.toString(countLeft));
			tweetCount.setText(Integer.toString(countLeft));
		}
		
		updateTweet();
	    
	    switch(getArguments().getInt(ARG_SECTION_NUMBER)){
	    	
	    case 1:
	    	
	    	GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
			MainActivity myActivity = new MainActivity();
		    gridview.setAdapter(myActivity.new ImageAdapter(getActivity()));
		    

		    gridview.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		            Toast.makeText(getActivity(), "" + position + v.toString() , Toast.LENGTH_SHORT).show();
		            Log.d("image", v.toString());
		            v.animate().scaleXBy(position);
		        }
		    });
	    	break;
	    
	    case 2:
	    	setUpMapIfNeeded();
	    	break;
	    	
	    case 4:
	    	Button submitButton = (Button) rootView.findViewById(R.id.button1);
			submitButton.setVisibility(View.VISIBLE);
			submitButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					callTweetIntent();
				}
			});		
			
	    	break;
	    	
	    } //end of switch
		
		return rootView;
	}
	
	
		
	Handler mTweetHandler = new Handler();
    Runnable mTweetRunnable = new Runnable() {
        @Override
        public void run() {
            String url = null;
            try {
                url = "http://www.twitter.com/intent/tweet?url=" + URLEncoder.encode(tweetText, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(Intent.ACTION_VIEW);
            try{
                Log.d("url",url);
                i.setDataAndNormalize(Uri.parse(url));
            }
            catch (Exception e){
                e.printStackTrace();
            }
            startActivity(i);
        }
    };
    
    private void callTweetIntent() {
        mTweetHandler.removeCallbacks(mTweetRunnable);
        mTweetHandler.post(mTweetRunnable);
    }
}
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}
