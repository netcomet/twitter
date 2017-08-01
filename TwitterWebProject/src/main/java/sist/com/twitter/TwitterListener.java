package sist.com.twitter;

import java.io.FileWriter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterListener implements StatusListener{

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatus(Status status) {
		// TODO Auto-generated method stub
	String data=status.getText();
	System.out.println(data);
	try {
		FileWriter fw= new FileWriter("/home/sist/twitter_data/twitter.txt",true);
		fw.write(data);
		fw.close();
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	}
	
	

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
