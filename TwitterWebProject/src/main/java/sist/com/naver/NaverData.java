package sist.com.naver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import org.apache.commons.httpclient.HttpConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class NaverData {
	
	public String createJson(String data){
	
		StringBuffer response = new StringBuffer();
		String clientId="FfqlCKJ5Eo2GK_BlhlHG";
		String clientSecret="O1UphBp0Ch";
		try {
		String text = URLEncoder.encode(data, "UTF-8");
		String anyURI = "https://openapi.naver.com/v1/search/blog?display=100&query="+text+"/";
		URL url = new URL(anyURI);
		HttpURLConnection connect = (HttpURLConnection)url.openConnection();
		connect.setRequestMethod("GET");
		connect.setRequestProperty("X-Naver-Client-Id", clientId);
		connect.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		int responseCode = connect.getResponseCode();
		BufferedReader br = null;
		if(responseCode ==200){
		br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		}else{
		br = new BufferedReader(new InputStreamReader(connect.getErrorStream()));
		}
		String inputLine = "";
		while((inputLine=br.readLine()) != null){
		response.append(inputLine+"\n");
		}
		br.close();
		System.out.println(response.toString());
		} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
		return response.toString();
	}
	
	public void createText(String json){
		try {
		String data="";
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONArray arr = (JSONArray)obj.get("items");
		System.out.println(arr.toJSONString());
		for (int i = 0; i < arr.size(); i++) {
		JSONObject jo = (JSONObject)arr.get(i);
		String desc = (String)jo.get("description");
		data+=desc+"\n";
		}
		data = data.substring(0,data.lastIndexOf("\n"));
		data = data.replace("[A-Za-z8-9]", "");
		data = data.replace("<", "");
		data = data.replace(">", "");
		data = data.replace("&", "");
		data = data.replace("#", "");
		data = data.replace("/", "");
		data = data.replace("*", "");
		data = data.replace(".", "");
		FileWriter fw = new FileWriter("/home/sist/r-data/blog1.txt");
		fw.write(data);
		fw.close();
		} catch (Exception e) {
		// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		NaverData naverData = new NaverData();
		String json=naverData.createJson("군함도");
		naverData.createText(json);
	}
}