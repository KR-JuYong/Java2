package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CookDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/cook";

	public Connection open() {
		Connection conn = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "cook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public List<Cook> getAll() throws Exception {
		Connection conn = open();
		List<Cook> cookList = new ArrayList<>();

		String sql = "select aid, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from cook";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Cook c = new Cook();
				c.setAid(rs.getInt("aid"));
				c.setTitle(rs.getString("title"));
				c.setDate(rs.getString("cdate"));
				cookList.add(c);
			}
			return cookList;
		}
	}

	public Cook getCook(int aid) throws SQLException {
		Connection conn = open();
		Cook c = new Cook();
		String sql = "select aid, title, ingredient, img, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from cook where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		try (conn; pstmt; rs) {
			c.setAid(rs.getInt("aid"));
			c.setTitle(rs.getString("title"));
			c.setIngredient(rs.getString("ingredient"));
			c.setImg(rs.getString("img"));
			c.setDate(rs.getString("cdate"));
			c.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return c;
		}
	}

	public void addCook(Cook c) throws Exception {
		Connection conn = open();
		String sql = "insert into cook (title,ingredient, img, date, content) values(?,?,?,current_timeStamp(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		try (conn; pstmt) {
			pstmt.setString(1, c.getTitle());
			pstmt.setString(2, c.getIngredient());
			pstmt.setString(3, c.getImg());
			pstmt.setString(4, c.getContent());
			pstmt.executeUpdate();
		}
	}

	public void delCook(int aid) throws SQLException {
		Connection conn = open();
		String sql = "delete from cook where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {
			pstmt.setInt(1, aid);
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB 에러");
			}
		}
	}
}
