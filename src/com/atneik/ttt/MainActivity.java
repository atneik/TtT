package com.atneik.ttt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new SectionFragment();
			Bundle args = new Bundle();
			args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class SectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		
		private String tweetText = null;
		
		private String disaster = null;
		private String location = null;
		private String category = null;
		private String moreInfo = null;
		
		public SectionFragment() {
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
		
		private void updateTweet(){
			tweetText = "";
			if(disaster != null)
				tweetText += "#" + disaster;
			if(location != null)
				tweetText += "#" + location;
			if(category != null)
				tweetText += "#" + category;
			if(moreInfo != null)
				tweetText += "#" + moreInfo;
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

}
