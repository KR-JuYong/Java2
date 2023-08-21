package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/MarketMall";
	
	
	
	
	//B2 접속
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn =  DriverManager.getConnection(JDBC_URL, "Market", "1111");
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return conn;
	}

	
	//유저정보 조회
	public User getUser (int mid) throws SQLException {
		Connection conn = open();
		
		User m = new User();
		
		//sql 수정
		String sql = "select mid, PARSEDATETIME(date, 'yyyy-mm-dd hh:mm:ss') as cdate, content from User where mid =?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  mid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try (conn; pstmt; rs) {
			m.setUser(rs.getString("mid"));
			m.setDate(rs.getString("cdate"));
			m.setEmail(rs.getString("content"));
			
			pstmt.executeQuery();
			return m;
		}
	}
	
	
	
	//비밀번호 변경하기
	public User getPassword (int mid) throws SQLException {
		Connection conn = open();
		
		User m = new User();
		
		//비밀번호 변경하기
		String sql = "select * from seller";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  mid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		try (conn; pstmt; rs) {
			m.setPassword(rs.getString("password"));
			
			pstmt.executeQuery();
			return m;
		}
	}
	
	
	//회원가입 등록
		public void register(User u) throws Exception {
			Connection conn = open();
			
			String sql = "insert into user(name,date,email,password) values(?,CURRENT_TIMESTAMP(),?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn; pstmt){
				pstmt.setString(1, u.getUser());
				pstmt.setString(2, u.getEmail());
				pstmt.setString(3, u.getPassword());
				pstmt.executeUpdate();
			} 
		}
		
	
	
}
