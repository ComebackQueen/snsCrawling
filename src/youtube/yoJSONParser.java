package youtube;

import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import database.SnsDAO;
import properties.SetProperties;

/*url �ҷ��ͼ� �ٷ� �Ľ�ó���ϰԲ� ��*/
public class yoJSONParser {
	SetProperties setting = new SetProperties();
	String apiKey=setting.properties.getProperty("yoApiKey");
	String channelId =setting.properties.getProperty("yoChannelId");
	
	public yoJSONParser() throws Exception{
		String address = "https://www.googleapis.com/youtube/v3/search?key="+ apiKey +"&channelId="+ channelId +"&part=snippet,id&order=date&maxResults=20";
		URL url;
		
		
		url = new URL(address);
		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
		JSONObject obj = (JSONObject)JSONValue.parseWithException(isr);
		JSONArray items = (JSONArray)obj.get("items");
		
		
		for(int i=0; i<items.size(); i++) {
			JSONObject tmp = (JSONObject)items.get(i);
			JSONObject snippet = (JSONObject)tmp.get("snippet");
			JSONObject id = (JSONObject)tmp.get("id");
			JSONObject thumbnails = (JSONObject)snippet.get("thumbnails");
			JSONObject defaults = (JSONObject)thumbnails.get("default");
			String videoId = (String)id.get("videoId");
			String title = (String)snippet.get("title");
			String publishedAt = (String)snippet.get("publishedAt");
			String channelTitle = (String)snippet.get("channelTitle");
			String url_thumbnails = (String)defaults.get("url");
			SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
			
			SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date data = recvSimpleFormat.parse(publishedAt);
			
			System.out.println("ä�θ�: " + channelTitle);
			System.out.println("����: " + title);
			System.out.println("�����: " + url_thumbnails);
			System.out.println("��ũ �ּ�: http://www.youtube.com/watch?v=" + videoId);
			System.out.println("�������: " + tranSimpleFormat.format(data));
			System.out.println("----------------------------------------------------------------------------------------");
		}
		
		try {
			SnsDAO db = new SnsDAO();
			String snsname = "Y";

			for(int i = 0; i < items.size(); i++) {
				JSONObject tmp = (JSONObject)items.get(i);
				JSONObject snippet = (JSONObject)tmp.get("snippet");
				JSONObject id = (JSONObject)tmp.get("id");
				JSONObject thumbnails = (JSONObject)snippet.get("thumbnails");
				JSONObject defaults = (JSONObject)thumbnails.get("default");
				String videoId = (String)id.get("videoId");
				String title = (String)snippet.get("title");
				String publishedAt = (String)snippet.get("publishedAt");
				String url_thumbnails = (String)defaults.get("url");
				SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000Z'");
				
				SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date data = recvSimpleFormat.parse(publishedAt);
				String createTime = tranSimpleFormat.format(data);
				
				String link = "http://www.youtube.com/watch?v=" + videoId;
				
				db.insertDB(snsname, title, link, url_thumbnails, createTime);
			}
			System.out.println("DB����Ϸ�!");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹��� �������� �ʽ��ϴ�.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
