package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MarketDAO {
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
	
	
	
	
	//판매 글 추가
	public void addProduct(Market n) throws Exception {
		Connection conn = open();
		
		String sql = "insert into product(title,img,date,content) values(?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		} 
	}
	
	 
	
	
	
	//판매 물건 목록 제공
	public List<Market> getAll() throws Exception {
		Connection conn = open();
		List<Market> marketList = new ArrayList<>();

		//sql 수정
		String sql = "select pid,  title, date, price from product";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Market m = new Market();
				m.setMid(rs.getInt("pid"));
				m.setTitle(rs.getString("title"));
				m.setDate(rs.getString("date"));
				m.setPrice(rs.getInt("price"));

				marketList.add(m);
			}
			return marketList;
		}

		
	}
	
	
	
	//클릭시 세부내용 제공 > 물건 상세정보 페이지로 이동 
	//물건 가져오기
	public Market getProduct(int mid) throws SQLException {
		Connection conn = open();
		
		Market m = new Market();
		
		//sql 수정
		String sql = "select pid, title, img, PARSEDATETIME(date, 'yyyy-mm-dd') as cdate, content, price from product where pid =?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  mid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try (conn; pstmt; rs) {
			m.setMid(rs.getInt("pid"));
			m.setTitle(rs.getString("title"));
			m.setImg(rs.getString("img"));
			m.setDate(rs.getString("cdate"));
			m.setContent(rs.getString("content"));
			
			pstmt.executeQuery();
			return m;
		}
	}

	
	/*//상품삭제
	public void delProduct(int mid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from tablename where mid=?";//table name 수정
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setInt(1, mid);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}*/
	
	
	//이메일 대조
	public User getUserByEmail(String email) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    User user = null;
	    
	    try {
	        conn = open();
	        String query = "SELECT * FROM users WHERE email = ?";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, email);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            user = new User();
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            // 사용자의 다른 필드들을 필요에 따라 설정해주어야 합니다.
	        }
	    } finally {
	        close(conn, pstmt, rs);
	    }
	    
	    return user;
	}
	
	// MarketDAO.java

	// 데이터베이스 연결 종료 및 리소스 반환
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
	    if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (pstmt != null) {
	        try {
	            pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    if (conn != null) {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void getShoppingcart(int mid) throws Exception {
		Connection conn = open();
		
		String sql = "INSERT INTO ShoppingCart (cartdate, pid) " +
	             	 "SELECT CURDATE(), p.pid " + //유저아이디는 나중에 추가해야함
	             	 "FROM Product p " +
	             	 "WHERE p.pid = ?";//table name 수정
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setInt(1, mid);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	public int totalCB(int uid) throws SQLException {
	      Connection conn = open();
	      String sql = "select SUM(usedcash) as total FROM cashbook WHERE cashbook.uid = 0 ";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      int total = 0;

	          try (conn; pstmt) {
	              //pstmt.setInt(1, uid);
	              ResultSet rs = pstmt.executeQuery();
	              if(rs.next()) {
	                 total = rs.getInt("total");
	              }
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
	      return total;
	   }
	
}
