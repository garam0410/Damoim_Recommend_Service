package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

    // oracle 계정
    String jdbcUrl = "jdbc:oracle:thin:@61.245.226.108:1521:orcl";
    String userId = "scott";
    String userPw = "1234";
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;
    ResultSet rs2 = null;

    String sql = "";
    String sql2 = "";
    String sql3 = "";
    String returns = "a";
    String returns2 = "b";

    int index;
    
    //로그인 인증
   public String connectionDB(String id, String pw) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            sql = "SELECT id,pw FROM USERINFORMATION WHERE id = ? and pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("id").equals(id) && rs.getString("pw").equals(pw)) {
                	returns2 = "true";
                }
                
                else {
                	returns2 = "false";
                }
            } else {
                returns2 = "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns2;
    }
    
    public String connectionDB(String name, String id, String pw, String email, String phone, String age, String job, String favorite, String area) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            sql = "SELECT id FROM USERINFORMATION WHERE id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "fail";
            } else {
            	
            	//마지막행 인덱스 조사
            	sql2 = "SELECT MAX(USERID) FROM USERINFORMATION";
            	pstmt2 = conn.prepareStatement(sql2);
            	rs2 = pstmt2.executeQuery();
            	if(rs2.next()) {
            		index = rs2.getInt("MAX(USERID)") + 1;
            		
            		System.out.println(index);
            		
                    sql3 = "INSERT INTO USERINFORMATION VALUES(?,?,?,?,?,?,?,?,?,?)";
                    pstmt2 = conn.prepareStatement(sql3);
                    pstmt2.setInt(1, index);
                    pstmt2.setString(2, name);
                    pstmt2.setString(3, id);
                    pstmt2.setString(4, pw);
                    pstmt2.setString(5, email);
                    pstmt2.setString(6, phone);
                    pstmt2.setInt(7, Integer.parseInt(area));
                    pstmt2.setInt(8, Integer.parseInt(age));
                    pstmt2.setInt(9, Integer.parseInt(job));
                    pstmt2.setString(10, favorite);
                    pstmt2.executeUpdate();
                    returns = "success";
            	}
            	
            	else {
                    sql3 = "INSERT INTO USERINFORMATION VALUES(?,?,?,?,?,?,?,?,?,?)";
                    pstmt2 = conn.prepareStatement(sql3);
                    pstmt2.setString(1, "1");
                    pstmt2.setString(2, name);
                    pstmt2.setString(3, id);
                    pstmt2.setString(4, pw);
                    pstmt2.setString(5, email);
                    pstmt2.setString(6, phone);
                    pstmt2.setInt(7, Integer.parseInt(area));
                    pstmt2.setInt(8, Integer.parseInt(age));
                    pstmt2.setInt(9, Integer.parseInt(job));
                    pstmt2.setString(10, favorite);
                    pstmt2.executeUpdate();
                    returns = "success";
            	}
            	

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }

}