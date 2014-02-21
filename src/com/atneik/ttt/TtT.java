package com.atneik.ttt;

import android.app.Application;
import android.util.Log;

public class TtT extends Application{
	
	private static String tweetText = "";
	private static Integer countLeft = 140;
	
	private static String disaster = null;
	private static String location = null;
	private static String category = null;
	private static String catMore = null;
	private static String moreInfo = null;
	
	private static void updateTweet(){

		tweetText = "";
		if(disaster != null)
			tweetText += "#" + disaster;
		if(location != null)
			tweetText += " #loc " + location;
		if(category != null)
			tweetText += " #" + category;
		if(catMore != null)
			tweetText += " " + catMore;
		if(moreInfo != null)
			tweetText += " #more " + moreInfo + " ";
		
		countLeft = 140 - tweetText.length();
		
		if(countLeft < 0){
			tweetText = tweetText.substring(0, 140);
		}
		Log.d("update-text",tweetText);
	}
	
	
	public static void setLocation(String loc){
    	location = loc;
    	Log.d("set-location",location);
    	updateTweet();
    }
	public static void setDisaster(String dis){
    	disaster = dis;
    	Log.d("set-disaster",disaster);
    	updateTweet();
    }
	public static void setCategory(String cat){
    	category = cat;
    	Log.d("set-category",category);
    	updateTweet();
    }
	public static void setMoreInfo(String more){
    	moreInfo = more;
    	Log.d("set-moreinfo",moreInfo);
    	updateTweet();
    }
	public static void setCatMore(String more){
    	catMore = more;
    	Log.d("set-moreinfo",catMore);
    	updateTweet();
    }
	
	public static String getTweetText(){
		return tweetText;
	}
	public static Integer getTweetLeft(){
		return countLeft;
	}
	public static void reset(){
		disaster = location = category =  moreInfo = catMore = null;
		updateTweet();
	}

}