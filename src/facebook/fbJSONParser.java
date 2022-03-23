package facebook;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.SnsDAO;

/* fbWebConnection���� �޾ƿ� json�����͸� ���� �Ľ��ؼ� ���ϴ� �����Ͱ� �����Բ� ���ش�.*/
public class fbJSONParser {
	public fbJSONParser() throws Exception{	
		fbWebConnection fb = new fbWebConnection();
		
		String json = fb.json;
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(json);
		JSONArray data = (JSONArray)obj.get("data");
		
		for(int i = 0; i < data.size(); i++) {
			JSONObject tmp = (JSONObject)data.get(i);
			String message = (String)tmp.get("message");
			String permalink_url = (String)tmp.get("permalink_url");
			String picture = (String)tmp.get("picture");
			String created_time = (String)tmp.get("created_time");
			Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(created_time);
			
			String newCreatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
			
			System.out.println("------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("�Խñ� ����: " + message);
			System.out.println("�̹��� ��ũ: " + picture);
			System.out.println("�Խñ� ��ũ: " + permalink_url);
			System.out.println("�������: " + newCreatedTime);
		}
		
		/*DB�� ��� �۾�*/
		try {
			SnsDAO db = new SnsDAO();
			String snsname = "F";
			
			for(int i = 0; i < data.size(); i++) {
				JSONObject tmp = (JSONObject)data.get(i);
				String message = (String)tmp.get("message");
				String permalink_url = (String)tmp.get("permalink_url");
				String picture = (String)tmp.get("picture");
				String created_time = (String)tmp.get("created_time");
				
				Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(created_time);
				
				String newCreatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
				
				db.insertDB(snsname, message, permalink_url, picture, newCreatedTime);
			}
			System.out.println("DB����Ϸ�!");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹��� �������� �ʽ��ϴ�.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
