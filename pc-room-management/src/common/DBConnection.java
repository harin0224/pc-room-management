package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static Connection conn = null;


    // DB 연결을 위해 공용커넥션 객체를 반환해주는 메소드
    public static Connection getConnection() {

        if (conn == null) {
            try {

                Properties prop = new Properties();
                prop.load(new FileReader("resources/driver.properties"));
                Class.forName(prop.getProperty("driver"));

                System.out.println("드라이버 등록성공");


                String url = prop.getProperty("url");

                conn = DriverManager.getConnection(url , prop);
                System.out.println("conn=" + conn);// 성공하면 connection 값, 실패하면 null값

                conn.setAutoCommit(false);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return conn;
    }


    public static void close(Connection con) {
        try {
            if(con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void commit(Connection con) {
        try {
            if(con != null && !con.isClosed()) {
                con.commit();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void rollback(Connection con) {
        try {
            if(con != null && !con.isClosed()) {
                con.rollback();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void close(Statement stmt) {
        try {
            if(stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}