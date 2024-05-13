package member.model.dao;

import member.model.dto.FoodDto;
import member.model.dto.OrderDto;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static common.DBConnection.close;
import static common.DBConnection.getConnection;

//    CREATE TABLE TBL_ORDER (
//            ORDERNO INT PRIMARY KEY,
//            ORDERTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//            USERID VARCHAR(50),
//            ITEMNAME VARCHAR(50),
//            CNT INT,
//            TOTALPRICE INT,
//            SERVING CHAR(1) DEFAULT 'n'
//            );

public class OrderDao {

    private Properties prop = null;

    public OrderDao() {

        try {
            prop = new Properties();
            prop.load(new FileReader("resources/query.properties"));
            //  prop.loadFromXML(new FileInputStream("mapper/query.xml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public ArrayList<OrderDto> selectByCompletedOrder(Connection conn) {
        ArrayList<OrderDto> list = null;

        Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체

        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'
        String sql= prop.getProperty("selectByCompletedOrder");

        try {

            // 3. 쿼리문을 실행할 statement 객체 생성
            stmt = conn.createStatement();

            // 4.쿼리문 전송, 실행결과를 ResultSet 으로 받기
            rset = stmt.executeQuery(sql);

            // 5. 받은결과값을 객체에 옮겨서 저장하기

            list = new ArrayList<OrderDto>();

            while (rset.next()) {

                OrderDto o = new OrderDto();

                /*
                private int order_no;
                private Date order_time;
                private String user_id;
                private String item_name;
                private int cnt;
                private int total_price;
                private String serving;
                */
                o.setOrder_no(rset.getInt("ORDERNO"));
                o.setOrder_time(rset.getDate("ORDERTIME"));
                o.setUser_id(rset.getString("USERID"));
                o.setItem_name(rset.getString("ITEMNAME"));
                o.setCnt(rset.getInt("CNT"));
                o.setTotal_price(rset.getInt("TOTALPRICE"));
                o.setServing(rset.getString("SERVING"));

                list.add(o);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //   throw new MemberException("selectAll 에러 : " + e.getMessage());
        } finally {
            close(rset);
            close(stmt);
        }


        return list;
    }


    public ArrayList<OrderDto> selectByCurrentOrder(Connection conn) {
        ArrayList<OrderDto> list = null;

        Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체

        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'
        String sql= prop.getProperty("selectByCurrentOrder");

        try {

            // 3. 쿼리문을 실행할 statement 객체 생성
            stmt = conn.createStatement();

            // 4.쿼리문 전송, 실행결과를 ResultSet 으로 받기
            rset = stmt.executeQuery(sql);

            // 5. 받은결과값을 객체에 옮겨서 저장하기

            list = new ArrayList<OrderDto>();

            while (rset.next()) {

                OrderDto o = new OrderDto();

                /*
                private int order_no;
                private Date order_time;
                private String user_id;
                private String item_name;
                private int cnt;
                private int total_price;
                private String serving;
                */
                o.setOrder_no(rset.getInt("ORDERNO"));
                o.setOrder_time(rset.getDate("ORDERTIME"));
                o.setUser_id(rset.getString("USERID"));
                o.setItem_name(rset.getString("ITEMNAME"));
                o.setCnt(rset.getInt("CNT"));
                o.setTotal_price(rset.getInt("TOTALPRICE"));
                o.setServing(rset.getString("SERVING"));

                list.add(o);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //   throw new MemberException("selectAll 에러 : " + e.getMessage());
        } finally {
            close(rset);
            close(stmt);
        }


        return list;
    }

    public int updateServing(OrderDto orderDto) {

        int result = 0;

        Connection conn = getConnection();
        PreparedStatement ps = null;
        String sql= prop.getProperty("updateMemberOrder");
        //updateMemberOrder=UPDATE TBL_ORDER SET SERVING = ? WHERE ORDERNO=?

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, "y");
            ps.setInt(2, orderDto.getOrder_no());

            result = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update 시 에러발생");

            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            close(ps);
        }


        return result;

    }
}
