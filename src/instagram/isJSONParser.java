package instagram;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.SnsDAO;
/* isWebConnection���� �޾ƿ� json�����͸� ���� �Ľ��ؼ� ���ϴ� �����Ͱ� �����Բ� ���ش�.*/
public class isJSONParser {
	public isJSONParser() throws Exception{
		isWebConnection is = new isWebConnection();
		
		String json = is.json;
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONArray data = (JSONArray)obj.get("data");
		
		for(int i = 0; i < data.size(); i++) {
			JSONObject tmp = (JSONObject)data.get(i);
			String caption = (String)tmp.get("caption");
			String media_url = (String)tmp.get("media_url");
			String permalink = (String)tmp.get("permalink");
			String timestamp = (String)tmp.get("timestamp");
			
			Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(timestamp);
			
			String newCreatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
			
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("�Խñ� ����: " + caption);
			System.out.println("�̹��� ��ũ: " + media_url);
			System.out.println("�Խñ� ��ũ: " + permalink);
			System.out.println("������� : " + newCreatedTime);
		}
		
		/*DB�� ��� �۾�*/
		try {
			SnsDAO db = new SnsDAO();
			String snsname = "I";

			
			for(int i = 0; i < data.size(); i++) {
				JSONObject tmp = (JSONObject)data.get(i);
				String caption = (String)tmp.get("caption"); // ����
				String media_url = (String)tmp.get("media_url"); // �ش� �� ��ũ
				String permalink = (String)tmp.get("permalink"); // �̹���
				String timestamp = (String)tmp.get("timestamp"); // �ۼ��ð�
				
				Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(timestamp);
				
				String newCreatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
				
				db.insertDB(snsname, caption, media_url, permalink, newCreatedTime);
			}
			System.out.println("DB����Ϸ�!");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹��� �������� �ʽ��ϴ�.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
