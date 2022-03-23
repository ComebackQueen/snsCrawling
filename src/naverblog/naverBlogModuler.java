package naverblog;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import database.SnsDAO;
import properties.SetProperties;


/* jdom이란 라이브러리를 기반으로 작성(xml파싱처리 용이)*/
public class naverBlogModuler {
	public naverBlogModuler() throws Exception{
		try {
			SetProperties setting = new SetProperties();
			String blogRss = setting.properties.getProperty("naverblogrss");
			SAXBuilder builder = new SAXBuilder();
			Document jdomdoc = builder.build(new java.net.URL(blogRss));
			
			Element root = jdomdoc.getRootElement();
			
			Element channel = root.getChild("channel");
			
			List<Element> item = channel.getChildren("item");
			
			for(int i=0; i<item.size(); i++) {
				Element items = item.get(i);
				SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss +0900", Locale.ENGLISH);
				
				SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				Date data = recvSimpleFormat.parse(items.getChild("pubDate").getValue());
				System.out.println("글 내용: " + items.getChild("description").getValue());
				System.out.println("링크: " + items.getChild("link").getValue());
				System.out.println("등록일자: " + tranSimpleFormat.format(data));
			}
			
			/*DB에 담는 작업*/
			try {
				SnsDAO db = new SnsDAO();
				String snsname = "N";

				for(int i = 0; i < item.size(); i++) {
					Element items = item.get(i);
					String description = items.getChild("description").getValue();
					String link = items.getChild("link").getValue();
					String pubDate = items.getChild("pubDate").getValue();
					SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss +0900", Locale.ENGLISH);
					
					SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
					Date data = recvSimpleFormat.parse(pubDate);
					String createTime = tranSimpleFormat.format(data);
					
					db.insertNaverDB(snsname, description, link, createTime);
				}
				System.out.println("DB저장완료!");
			} catch(ClassNotFoundException e) {
				System.out.println("드라이버가 존재하지 않습니다.");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
