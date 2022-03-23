package instagram;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.SnsDAO;
/* isWebConnection에서 받아온 json데이터를 이제 파싱해서 원하는 데이터가 나오게끔 해준다.*/
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
			System.out.println("게시글 내용: " + caption);
			System.out.println("이미지 링크: " + media_url);
			System.out.println("게시글 링크: " + permalink);
			System.out.println("등록일자 : " + newCreatedTime);
		}
		
		/*DB에 담는 작업*/
		try {
			SnsDAO db = new SnsDAO();
			String snsname = "I";

			
			for(int i = 0; i < data.size(); i++) {
				JSONObject tmp = (JSONObject)data.get(i);
				String caption = (String)tmp.get("caption"); // 내용
				String media_url = (String)tmp.get("media_url"); // 해당 글 링크
				String permalink = (String)tmp.get("permalink"); // 이미지
				String timestamp = (String)tmp.get("timestamp"); // 작성시간
				
				Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(timestamp);
				
				String newCreatedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
				
				db.insertDB(snsname, caption, media_url, permalink, newCreatedTime);
			}
			System.out.println("DB저장완료!");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버가 존재하지 않습니다.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
