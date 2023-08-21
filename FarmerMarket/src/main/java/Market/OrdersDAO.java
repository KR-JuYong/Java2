package Market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
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

	// 주문내역
	public List<Orders> getAll() throws Exception {
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

	}

	// 클릭시 세부내용 제공 > 주문 상세정보 페이지로 이동
	// 주문내역 가져오기
	public Orders getOrder(int orderid) throws SQLException {
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

	// 주문 취소 > 주문 취소 시 사용자 주문내역에서 삭제 및 판매자 판매내역에서 삭제.
	public void CancleOrders(int orderid) throws SQLException {
		Connection conn = open();

		String sql = "delete from Orders where orderid=?";// table name 수정
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setInt(1, orderid);
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}

	// 주문하기
	public void addOrders(Orders o) throws Exception {
		Connection conn = open();
		String sql = "INSERT INTO Orders (orderid, mid, ptid, price, orderdate) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setInt(1, o.getOrderId());
			pstmt.setString(2, o.getUserId());
			pstmt.setInt(3, o.getProductId());
			pstmt.setDouble(4, o.getPrice());

			java.util.Date currentDate = new java.util.Date();
			java.sql.Date orderDate = new java.sql.Date(currentDate.getTime());
			pstmt.setDate(5, orderDate);

			// Execute the query
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}