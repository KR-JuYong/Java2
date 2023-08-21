package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDAO {
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
				sc.setInfo(rs.getString("title"), rs.getInt("price"));
	            //sc.setTitle(rs.getString("title"));
	            //sc.setPrice(rs.getInt("price"));
	            sc.setTotal(rs.getInt("total"));
	            sc.setCount(rs.getInt("count"));
	            shoppingcartList.add(sc);
				

			}
			return shoppingcartList;
		}
	}
	
	
	
}
