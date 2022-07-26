package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserinfoDAO {

   Connection conn = null;
   PreparedStatement psmt = null;
   ResultSet rs = null;
   boolean check = false;
   
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "cgi_1_0418_3";
			String password = "smhrd3";
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
   public boolean insertUser(User u) {

      try {
         getConn();

         String sql = "INSERT INTO member VALUES(?,?)";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, u.getId());
         psmt.setString(2, u.getPassword());

         int cnt = psmt.executeUpdate();

         if (cnt > 0) {
            check = true;
            
         } else {
            check = false;
         }
      } catch (Exception e) {
         System.out.println("중복된 아이디거나 잘못된 형식입니다!");
      } finally {
         getClose();
      }
      return check;

   }
   public void getClose() {

		try {
			if (rs != null)
				rs.close();
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("자원반납시 생긴 오류!");
		}

	}

   public boolean login(User u) {
      boolean check1 = false;
      try {
         getConn();
         String sql = "SELECT ID, pw FROM member where id = ? and pw =?";
         psmt = conn.prepareStatement(sql);

         psmt.setString(1, u.getId());
         psmt.setString(2, u.getPassword());

         rs = psmt.executeQuery();
         check1 = rs.next();

      }

      catch (Exception e) {
         e.printStackTrace();
      } finally {
         getClose();
      }
      return check1;
   }
}