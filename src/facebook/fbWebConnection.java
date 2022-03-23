package facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import properties.SetProperties;
/* accessToken을 받아서 accessToken을 이용하여 원하는 데이터들을 받아온다 */
public class fbWebConnection {
	String json="";
	SetProperties setting = new SetProperties();
	String accessToken = setting.properties.getProperty("fbAccessToken");
	
	public fbWebConnection() throws Exception{
		String address = "https://graph.facebook.com/v3.0/654495368218889/feed?fields=message,permalink_url,picture,created_time&access_token=" + accessToken;		
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		json = br.readLine(); //url을 통해 json 데이터가 전부 나오게끔 한다
	}
}
