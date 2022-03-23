package twitter;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import database.SnsDAO;
import properties.SetProperties;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
/* 트위터는 라이브러리가 따로 제공됨(twitter4j)*/
public class twitterModuler{
	public twitterModuler() throws Exception{
		try {
			SetProperties setting = new SetProperties();
			String ac = setting.properties.getProperty("twAccessToken"); // 액세스 토큰
			String acs = setting.properties.getProperty("twAccessTokenSecret"); // 액세스 토큰 시크릿
			String ck = setting.properties.getProperty("twCustomerKey"); // 컨슈머키
			String cs = setting.properties.getProperty("twCustomerSecret"); // 컨슈머 시크릿
			
			AccessToken accesstoken = new AccessToken(ac,acs);
			// ("Access Token", "Access Token Secret")
			Twitter twitter = TwitterFactory.getSingleton();
			twitter.setOAuthConsumer(ck, cs);
			// ("Consumer Key(API Key)", "Consumer Secret (API Secret)") 
			twitter.setOAuthAccessToken(accesstoken);
			User user = twitter.verifyCredentials();
			
			List<Status> list = twitter.getUserTimeline();
			System.out.println();
			System.out.println();
			
			/*데이터들을 게시글 단위별로 가져옴*/
			for(Status status : list) {
				MediaEntity[] media = status.getMediaEntities(); // 이미지 불러오기 위한 객체
				
				System.out.println("작성자:" + status.getUser().getScreenName());
				System.out.println("타임라인내용:" + status.getText());
				String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); // 해당 게시글 url
				System.out.println("링크 주소:" + url);
				for(MediaEntity m : media) {
					System.out.println("이미지 링크: "+m.getMediaURLHttps());
				}
				SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				Date data = recvSimpleFormat.parse(status.getCreatedAt().toString());
				System.out.println("등록일자: " + tranSimpleFormat.format(data));
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
			}			
			
			/*DB에 담는 작업*/
			try {
				SnsDAO db = new SnsDAO();
				String snsname = "T";
			
				for(Status status : list) {
					MediaEntity[] media = status.getMediaEntities(); // 이미지 불러오기 위한 객체
					
					String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); // 해당 게시글 url
					
					for(MediaEntity m : media) {
						SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
						
						SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
						Date data = recvSimpleFormat.parse(status.getCreatedAt().toString());
						String createTime = tranSimpleFormat.format(data);
						
						db.insertDB(snsname, status.getText(), url, m.getMediaURLHttps(), createTime);
					}
					
				}
				System.out.println("DB저장완료!");
			} catch(ClassNotFoundException e) {
				System.out.println("드라이버가 존재하지 않습니다.");
			} catch(SQLException e) {
				e.printStackTrace();
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
