package instagram;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import properties.SetProperties;
/* accessToken�� �޾Ƽ� accessToken�� �̿��Ͽ� ���ϴ� �����͵��� �޾ƿ´� */
public class isWebConnection {
	String json="";
	SetProperties setting = new SetProperties();
	
	String accessToken = setting.properties.getProperty("isAccessToken");
	
	public isWebConnection() throws Exception{
		String address = "https://graph.facebook.com/v3.0/17841408081458408/media?fields=caption,media_url,permalink,timestamp&access_token=" + accessToken;		
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";
		
		url = new URL(address);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		json = br.readLine(); //url�� ���� json �����Ͱ� ���� �����Բ� �Ѵ�
	}
}
