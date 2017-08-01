package sist.com.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sist.com.r.RManager;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner job;
	@Autowired
	private RManager rManager;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("list.do")

	public void main_twitter() {
		hadoopFileDelete();
		copyFromLocal();
		try {
			job.call();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		copyToLocal();
		rManager.rGraph();

	}

	public void hadoopFileDelete() {

		try {
			FileSystem fs = FileSystem.get(conf);
			if (fs.exists(new Path("/twitter_output"))) {
				fs.delete(new Path("/twitter_output"), true);

			}
			fs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 리눅스에서 하둡
	public void copyFromLocal() {

		try {
			FileSystem fs = FileSystem.get(conf);
			fs.copyFromLocalFile(new Path("/home/sist/twitter_data/twitter.txt"),
					new Path("/twitter_input/twitter.txt"));
			fs.close();
		} catch (Exception e) {

			// TODO: handle exception
		}
	}

	// 하둡에서 리눅스
	public void copyToLocal() {
		try {
			FileSystem fs = FileSystem.get(conf);
			fs.copyToLocalFile(new Path("/twitter_output/part-r-00000"),
					new Path("/home/sist/twitter_data/twitter_result"));
			fs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) { logger.info(
	 * "Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * return "home"; }
	 */

}
