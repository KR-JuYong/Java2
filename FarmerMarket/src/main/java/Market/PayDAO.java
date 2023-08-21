package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PayDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/MarketMall";
	
	// B2 접속
		public Connection open() {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, "Market", "1111");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		//결제 정보 추가
	public void addPay(Pay p) throws Exception{
		Connection conn =open();
		String sql="insert into Pay(name,phonenumber,name2,phonenumber2,address,request,cardnumber,cvc,date) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		try(conn; pstmt){
			pstmt.setString(1,p.getName());
			pstmt.setString(2,p.getPhonenumber());
			pstmt.setString(3,p.getName2());
			pstmt.setString(4, p.getPhonenumber2());
			pstmt.setString(5, p.getAddress());
			pstmt.setString(6, p.getRequest());
			pstmt.setString(7, p.getCardnumber());
			pstmt.setString(8, p.getCvc());
			pstmt.setString(9, p.getDate());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//총 결제 금액 안내
	public int getTotalPrice() throws Exception {
	    Connection conn = open();
	    int totalPrice = 0;

	    String sql = "SELECT SUM(price) AS total FROM cartitem";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        if (rs.next()) {
	            totalPrice = rs.getInt("total");
	        }
	    }

	    return totalPrice;
	}

}
