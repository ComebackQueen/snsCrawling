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
/* Ʈ���ʹ� ���̺귯���� ���� ������(twitter4j)*/
public class twitterModuler{
	public twitterModuler() throws Exception{
		try {
			SetProperties setting = new SetProperties();
			String ac = setting.properties.getProperty("twAccessToken"); // �׼��� ��ū
			String acs = setting.properties.getProperty("twAccessTokenSecret"); // �׼��� ��ū ��ũ��
			String ck = setting.properties.getProperty("twCustomerKey"); // ������Ű
			String cs = setting.properties.getProperty("twCustomerSecret"); // ������ ��ũ��
			
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
			
			/*�����͵��� �Խñ� �������� ������*/
			for(Status status : list) {
				MediaEntity[] media = status.getMediaEntities(); // �̹��� �ҷ����� ���� ��ü
				
				System.out.println("�ۼ���:" + status.getUser().getScreenName());
				System.out.println("Ÿ�Ӷ��γ���:" + status.getText());
				String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); // �ش� �Խñ� url
				System.out.println("��ũ �ּ�:" + url);
				for(MediaEntity m : media) {
					System.out.println("�̹��� ��ũ: "+m.getMediaURLHttps());
				}
				SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
				Date data = recvSimpleFormat.parse(status.getCreatedAt().toString());
				System.out.println("�������: " + tranSimpleFormat.format(data));
				System.out.println("----------------------------------------------------------------------------------------------------------------------");
			}			
			
			/*DB�� ��� �۾�*/
			try {
				SnsDAO db = new SnsDAO();
				String snsname = "T";
			
				for(Status status : list) {
					MediaEntity[] media = status.getMediaEntities(); // �̹��� �ҷ����� ���� ��ü
					
					String url = "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId(); // �ش� �Խñ� url
					
					for(MediaEntity m : media) {
						SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
						
						SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
						Date data = recvSimpleFormat.parse(status.getCreatedAt().toString());
						String createTime = tranSimpleFormat.format(data);
						
						db.insertDB(snsname, status.getText(), url, m.getMediaURLHttps(), createTime);
					}
					
				}
				System.out.println("DB����Ϸ�!");
			} catch(ClassNotFoundException e) {
				System.out.println("����̹��� �������� �ʽ��ϴ�.");
			} catch(SQLException e) {
				e.printStackTrace();
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
