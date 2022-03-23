package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import properties.SetProperties;

public class SnsDAO {
	public String sql = "";
	public Connection con;

	public PreparedStatement stmt = null;
	public ResultSet rs = null;
	
	public SnsDAO() throws Exception {		
			SetProperties setting = new SetProperties();
			String url = setting.properties.getProperty("dburl");
			String user = setting.properties.getProperty("user");
			String pw = setting.properties.getProperty("pw");
			
			Class.forName(setting.properties.getProperty("driverName"));
			System.out.println("드라이버 로드 완료");
			
			con = DriverManager.getConnection(url, user, pw);
			if(con != null)
				System.out.println("postgresql 접속 성공");
	}
	
	public void insertDB(String snsName, String context, String link, String picture, String CreatedTime) {
		try {
			sql = "insert into sns values(nextval('sns_no_seq'),?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, snsName);
			stmt.setString(2, context);
			stmt.setString(3, link);
			stmt.setString(4, picture);
			stmt.setString(5, CreatedTime);
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void insertNaverDB(String snsName, String context, String link, String CreatedTime) {	
		try {
			sql = "insert into sns(no, snsname, post_context, post_link, timestamp) values(nextval('sns_no_seq'),?,?,?,?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, snsName);
			stmt.setString(2, context);
			stmt.setString(3, link);
			stmt.setString(4, CreatedTime);
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
