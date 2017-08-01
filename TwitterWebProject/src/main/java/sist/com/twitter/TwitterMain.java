package sist.com.twitter;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterMain {
	
	
	public static void main(String[] args) {
		try {
			String[] data={
					"이다지",
					"송중기",
					"송혜교",
					"아이유",
					"치킨"
			};
			
			TwitterStream ts= new TwitterStreamFactory().getInstance();
			TwitterListener listener=new TwitterListener();
			ts.addListener(listener);
			FilterQuery fq=new FilterQuery();
			fq.track(data);
			ts.filter(fq);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
