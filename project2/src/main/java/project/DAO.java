package project;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://locallhost/~/Selenium";

	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "Selenium", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public List<Selenium2> getAll2() throws Exception {
		Connection conn = open();
		List<Selenium2> Selenium2s = new ArrayList<>();

		String sql = "select id, 종목이름, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from Selenium2";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Selenium2 s2 = new Selenium2();
				s2.setId(rs.getInt("id"));
				s2.set종목이름(rs.getString("종목이름"));
				s2.setDate(rs.getString("cdate"));
				Selenium2s.add(s2);
			}
			return Selenium2s;
		}
	}
	
	public Selenium2 getSelenium2(int id) throws SQLException{
		Connection conn=open();
		Selenium2 s2=new Selenium2();
		String sql="select id, img, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, 종목이름, 시가총액, 상장주식수, _52주최고52주최저 from selenium2 where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs= pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs){
			s2.setId(rs.getInt("id"));
			s2.setImg(rs.getString("img"));
			s2.setDate(rs.getString("cdate"));
			s2.set종목이름(rs.getString("종목이름"));
			s2.set시가총액(rs.getString("시가총액"));
			s2.set상장주식수(rs.getString("상장주식수"));
			s2.set_52주최고52주최저(rs.getString("_52주최고52주최저"));
			pstmt.executeQuery();
			return s2;
		}
	}
	
	public List<Selenium3> getAll3() throws Exception {
		Connection conn = open();
		List<Selenium3> Selenium3s = new ArrayList<>();

		String sql = "select id, 종목이름, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from Selenium3";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Selenium3 s3 = new Selenium3();
				s3.setId(rs.getInt("id"));
				s3.set종목이름(rs.getString("종목이름"));
				s3.setDate(rs.getString("cdate"));
				Selenium3s.add(s3);
			}
			return Selenium3s;
		}
	}
	
	public Selenium3 getSelenium3(int id) throws SQLException{
		Connection conn=open();
		Selenium3 s3=new Selenium3();
		String sql="select id, img, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, 종목이름, 시가총액, 상장주식수, _52주최고52주최저, 기초지수, 유형, 상장일, 펀드보수, 자산운용사, Nav, _1개월수익률, _3개월수익률,_6개월수익률,_12개월수익률 from selenium3 where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs= pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs){
			s3.setId(rs.getInt("id"));
			s3.setImg(rs.getString("img"));
			s3.setDate(rs.getString("cdate"));
			s3.set종목이름(rs.getString("종목이름"));
			s3.set시가총액(rs.getString("시가총액"));
			s3.set상장주식수(rs.getString("상장주식수"));
			s3.set_52주최고52주최저(rs.getString("_52주최고52주최저"));
			s3.set기초지수(rs.getString("기초지수"));
			s3.set유형(rs.getString("유형"));
			s3.set상장일(rs.getString("상장일"));
			s3.set펀드보수(rs.getString("펀드보수"));
			s3.set자산운용사(rs.getString("자산운용사"));
			s3.setNav(rs.getString("Nav"));
			s3.set_1개월수익률(rs.getString("_1개월수익률"));
			s3.set_3개월수익률(rs.getString("_3개월수익률"));
			s3.set_6개월수익률(rs.getString("_6개월수익률"));
			s3.set_12개월수익률(rs.getString("_12개월수익률"));

			pstmt.executeQuery();
			return s3;
		}
	}
	
	public List<Selenium4> getAll4() throws Exception {
		Connection conn = open();
		List<Selenium4> Selenium4s = new ArrayList<>();

		String sql = "select id, 종목이름, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from Selenium4";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		try (conn; pstmt; rs) {
			while (rs.next()) {
				Selenium4 s4 = new Selenium4();
				s4.setId(rs.getInt("id"));
				s4.set종목이름(rs.getString("종목이름"));
				s4.setDate(rs.getString("cdate"));
				Selenium4s.add(s4);
			}
			return Selenium4s;
		}
	}
	
	public Selenium4 getSelenium4(int id) throws SQLException{
		Connection conn=open();
		Selenium4 s4=new Selenium4();
		String sql="select id, img, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, 종목이름, 시가총액, 시가총액순위, 상장주식수, 액면가매매단위, 외국인한도주식수, 외국인보유주식수, 외국인소진율, 투자의견목표주가, _52주최고52주최저, PEREPS, 추정PEREPS, PBRBPS, 배당수익률, 동일업종PER, 동일업종등락률  from selenium4 where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs= pstmt.executeQuery();
		rs.next();
		
		try(conn; pstmt; rs){
			s4.setId(rs.getInt("id"));
			s4.setImg(rs.getString("img"));
			s4.setDate(rs.getString("cdate"));
			s4.set종목이름(rs.getString("종목이름"));
			s4.set시가총액(rs.getString("시가총액"));
			s4.set시가총액순위(rs.getString("시가총액순위"));
			s4.set상장주식수(rs.getString("상장주식수"));
			s4.set액면가매매단위(rs.getString("액면가매매단위"));
			s4.set외국인한도주식수(rs.getString("외국인한도주식수"));
			s4.set외국인보유주식수(rs.getString("외국인보유주식수"));
			s4.set외국인소진율(rs.getString("외국인소진율"));
			s4.set투자의견목표주가(rs.getString("투자의견목표주가"));
			s4.set_52주최고52주최저(rs.getString("_52주최고52주최저"));
			s4.setPEREPS(rs.getString("PEREPS"));
			s4.set상장주식수(rs.getString("상장주식수"));
			s4.set추정PEREPS(rs.getString("추정PEREPS"));
			s4.setPBRBPS(rs.getString("PBRBPS"));
			s4.set배당수익률(rs.getString("배당수익률"));
			s4.set동일업종PER(rs.getString("동일업종PER"));
			s4.set동일업종등락률(rs.getString("동일업종등락률"));
			pstmt.executeQuery();
			return s4;
		}
	}
}
