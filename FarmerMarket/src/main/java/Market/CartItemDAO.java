package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {
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
	
	// 장바구니 추가
	public void addCartItem(CartItem ci) throws Exception {
		Connection conn = open();
		
		String sql = "insert into cartitem(cartid, pid, price) values(?,?,?,?)"; // 테이블명 수정
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setInt(1, ci.getCartId());
			pstmt.setInt(2, ci.getProductId());
			pstmt.setDouble(4, ci.getPrice());
			pstmt.executeUpdate();
		}
	}
	
	// 장바구니에서 목록 보여주기
	
	// 장바구니 취소
	public void delCartItem(int cartId) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from cartitem where cartid=?"; // 테이블명 수정
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setInt(1, cartId);
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
	/*public int getTotalPrice() throws Exception {
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
	}*/
	
	
	public List<ShoppingCart> getAll() throws Exception {
		Connection conn = open();
		List<ShoppingCart> shoppingcartList = new ArrayList<>();

		String sql = "SELECT p.title, p.price, sum(p.price) as total, count(sc.pid) as count "
				+ "FROM ShoppingCart AS sc "
				+ "JOIN Product AS p ON sc.pid = p.pid "
				+ "group by p.pid, p.title, p.price";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				ShoppingCart sc = new ShoppingCart();
	   
	            sc.setTitle(rs.getString("title"));
	            sc.setPrice(rs.getInt("price"));
	            sc.setTotal(rs.getInt("total"));
	            sc.setCount(rs.getInt("count"));
	            shoppingcartList.add(sc);
				

			}
			return shoppingcartList;
		}
	}

}