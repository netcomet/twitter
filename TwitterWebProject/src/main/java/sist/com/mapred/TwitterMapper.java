package sist.com.mapred;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TwitterMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private final IntWritable one = new IntWritable(1);
	private Text res = new Text(); 
	
		String[] data={
				"이다지",
				"송중기",
				"송혜교",
				"레드벨벳",
				"아이유"
		};
		
		Pattern [] p = new Pattern[data.length];
		Matcher [] m = new Matcher[data.length];
		
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		for (int i = 0; i < p.length; i++) {
			p[i] = Pattern.compile(data[i]);
			System.out.println("p["+i+"]="+p[i]);
		}
		for (int i = 0; i < m.length; i++) {
			m[i] = p[i].matcher(value.toString());
			while (m[i].find()) {
				res.set(m[i].group());
				System.out.println("m[i].group()="+m[i].group());
				context.write(res, one);
			}
		}
	}
}
