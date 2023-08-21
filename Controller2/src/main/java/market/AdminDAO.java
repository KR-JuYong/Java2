package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/MarketMall";
	
	
	//H2 접속
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
	
	
	public void addProduct(Market n) throws Exception {
		Connection conn = open();
		
		String sql = "insert into product(title,img,date,price,content) values(?,?,CURRENT_TIMESTAMP(),?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setInt(3, n.getPrice());
			pstmt.setString(4, n.getContent());
			pstmt.executeUpdate();
		} 
	}
	
	//뉴스 클릭시 세부내용 보여주기
	public List<Market> getAll() throws Exception {
		Connection conn = open();
		List<Market> productList = new ArrayList<>();

		String sql = "select pid, title, date, price from product";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Market n = new Market();
				n.setPid(rs.getInt("pid"));
				n.setTitle(rs.getString("title"));
				n.setDate(rs.getString("date"));
				n.setPrice(rs.getInt("price"));

				productList.add(n);
			}
			return productList;
		}

		
	}
	
	
	public Market getProduct(int pid) throws SQLException {
		Connection conn = open();
		
		Market n = new Market();
		
		String sql = "select pid, title, img, PARSEDATETIME(date, 'yyyy-mm-dd') as cdate, content, price from product where pid =?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  pid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try (conn; pstmt; rs) {
			n.setPid(rs.getInt("pid"));
			n.setTitle(rs.getString("title"));
			n.setImg(rs.getString("img"));
			n.setDate(rs.getString("cdate"));
			n.setContent(rs.getString("content"));
			n.setPrice(rs.getInt("price"));
			
			pstmt.executeQuery();
			return n;
		}
	}
	
	public void delProduct(int pid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from product where pid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt){
			pstmt.setInt(1, pid);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
	
	/*public Orders getOrder(int orderid) throws SQLException {
		Connection conn = open();

		Orders o = new Orders();

		// sql 수정
		String sql = "select orderid, ptid, mid, price, orderdate from orders where orderid=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderid);
		ResultSet rs = pstmt.executeQuery();

		rs.next();

		try (conn; pstmt; rs) {
			o.setOrderId(rs.getInt("orderid"));
			o.setProductId(rs.getInt("ptid"));
			o.setUserId(rs.getString("mid"));
			o.setPrice(rs.getInt("price"));
			o.setOrderDate(rs.getDate("orderdate"));

			pstmt.executeQuery();
			return o;
		}
	}
	
	public List<Orders> getAllorders() throws Exception {
		Connection conn = open();
		List<Orders> orderList = new ArrayList<>();

		// sql 수정
		String sql = "select orderid, ptid, price, orderdate from Orders ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Orders o = new Orders();
				o.setOrderId(rs.getInt("orderid"));
				o.setProductId(rs.getInt("ptid"));
				o.setPrice(rs.getInt("price"));
				o.setOrderDate(rs.getDate("orderdate"));

				orderList.add(o);
			}
			return orderList;
		}

	}*/

}
