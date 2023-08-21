package ch09;
import java.sql.Connection;
import java.sql.DriverManager;



public class H2DBConnectionTest {
    public static void main(String[] args) {
        try {
            // H2 JDBC Driver를 로딩합니다.
            Class.forName("org.h2.Driver");
            // H2 데이터베이스에 연결합니다.
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/jwbookdb", "jwbook", "1234");
if(conn != null){
     // 성공 메시지를 출력합니다.
            System.out.println("H2 데이터베이스에 성공적으로 연결되었습니다.");
// 연결을 닫습니다.
            conn.close();
}else{
System.out.println("H2 데이터베이스 접속에 문제가 있습니다."); 
}              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}