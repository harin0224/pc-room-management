package member.model.dao;

import member.model.dto.FoodDto;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static common.DBConnection.close;
import static common.DBConnection.getConnection;

//    CREATE TABLE FOOD (
//            ITEMCODE INT PRIMARY KEY,
//            ITEMNAME VARCHAR(50),
//            ITEMPRICE INT
//            );

public class FoodDao {

    private Properties prop = null;




    public FoodDao() {


        try {
            prop = new Properties();
            prop.load(new FileReader("resources/query.properties"));
            //  prop.loadFromXML(new FileInputStream("mapper/query.xml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public ArrayList<FoodDto> selectAllFood(Connection conn) {

        ArrayList<FoodDto> list = null;


        Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체

        //String sql = "SELECT * FROM MEMBER";// 자동으로 세미콜론을 붙여 실행되므로 붙히지않는다
        String sql= prop.getProperty("selectAll");
        try {


            // 3. 쿼리문을 실행할 statement 객체 생성
            stmt = conn.createStatement();

            // 4.쿼리문 전송, 실행결과를 ResultSet 으로 받기
            rset = stmt.executeQuery(sql);

            // 5. 받은결과값을 객체에 옮겨서 저장하기

            list = new ArrayList<FoodDto>();

            while (rset.next()) {

                FoodDto f = new FoodDto();

                f.setItem_code(rset.getInt("ITEMCODE"));
                f.setItem_name(rset.getString("ITEMNAME"));
                f.setItem_price(rset.getInt("ITEMPRICE"));

                list.add(f);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(rset);
            close(stmt);
        }
        return list;
    }

    public int insertFood(Connection conn, FoodDto foodDto) {

        int result = 0;

        PreparedStatement ps = null;
        String sql= prop.getProperty("insertMember");


        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, foodDto.getItem_name());
            ps.setInt(2, foodDto.getItem_price());

            result = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insert 시 에러발생");

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

    public int updateFood(Connection conn, FoodDto foodDto1, FoodDto foodDto2) {

        int result = 0;

        PreparedStatement ps = null;
        String sql= prop.getProperty("updateMember");
        //updateMember=UPDATE FOOD SET ITEMNAME = ? , ITEMPRICE = ? WHERE ITEMNAME=?

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, foodDto2.getItem_name());
            ps.setInt(2, foodDto2.getItem_price());
            ps.setString(3, foodDto1.getItem_name());

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

    public int deleteFood(Connection conn, FoodDto foodDto) {

        int result = 0;

        PreparedStatement ps = null;
        String sql= prop.getProperty("deleteMember");
        //deleteMember=DELETE FROM FOOD WHERE ITEMNAME=?

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, foodDto.getItem_name());

            result = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("delete 시 에러발생");

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
