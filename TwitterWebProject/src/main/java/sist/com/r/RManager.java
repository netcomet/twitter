package sist.com.r;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

@Component
public class RManager {
	
	public void rGraph(){
		
		try {
			RConnection  rc = new RConnection();
			rc.voidEval("data <- read.table(\"/home/sist/twitter_data/twitter_result\")");
			rc.voidEval("png(\"/home/sist/Documents/workspace/TwitterWeb/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/TwitterWebProject/twitter.png\")");
			rc.voidEval("barplot(data$V2,names.arg=data$V1,col=rainbow(20))");
			rc.voidEval("dev.off()");
			rc.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void nGraph(){
		
		try {
			RConnection  rc = new RConnection();
			rc.voidEval("data <- read.table(\"/home/sist/naver_data/naver_result\")");
			rc.voidEval("png(\"/home/sist/Documents/workspace/TwitterWeb/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/TwitterWebProject/naver.png\")");
			rc.voidEval("barplot(data$V2,names.arg=data$V1,col=rainbow(20))");
			rc.voidEval("dev.off()");
			rc.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
