package facebook;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.SnsDAO;

/* fbWebConnection에서 받아온 json데이터를 이제 파싱해서 원하는 데이터가 나오게끔 해준다.*/
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
			System.out.println("게시글 내용: " + message);
			System.out.println("이미지 링크: " + picture);
			System.out.println("게시글 링크: " + permalink_url);
			System.out.println("등록일자: " + newCreatedTime);
		}
		
		/*DB에 담는 작업*/
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
			System.out.println("DB저장완료!");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버가 존재하지 않습니다.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
