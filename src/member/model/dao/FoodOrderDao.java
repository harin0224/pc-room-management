package member.model.dao;

import member.model.dto.FoodDto;
import member.model.dto.OrderDto;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.util.ArrayList;

import static common.DBConnection.close;
import static common.DBConnection.getConnection;

public class FoodOrderDao {
    private Properties prop = null;
    private Connection conn = null;

    public FoodOrderDao() {
        try {
            prop = new Properties();
            //memberQuery.properties -> pcmember 테이블 접근
            //query.properties -> food, order 테이블 접근
            prop.load(new FileReader("resources/query.properties"));
            conn = getConnection();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   // 여기까지 기본 세팅, 이후 메서드 작성하시면 됩니다.




    // 고객 음식 주문 시, 테이블에 주문된 음식 추가
    public int foodOrder(Connection conn, OrderDto orderDto){

        int result = 0;

        PreparedStatement ps = null;

        //insertMemberOrder=INSERT INTO TBL_ORDER VALUES (null, sysdate, ?, ?, ?, ?, 'n') -> user_id, item_name, cnt, total_price
        String sql= prop.getProperty("insertMemberOrder");


        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, orderDto.getUser_id());
            ps.setString(2, orderDto.getItem_name());
            ps.setInt(3, orderDto.getCnt());
            ps.setInt(4, orderDto.getTotal_price());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("고객 음식주문 시 에러발생");

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

    // 고객이 결제,주문한 본인의 주문내역 확인
    public ArrayList<OrderDto> foodordercheck(){
        return null;
    }

    public FoodDto selectFoodPriceByFoodName(Connection conn, FoodDto foodDto1) {

        FoodDto foodDto = null;

        PreparedStatement pstmt = null;
        ResultSet rset = null;


        //selectFoodPriceByFoodName=SELECT * FROM FOOD WHERE ITEMNAME = ?
        String sql= prop.getProperty("selectFoodPriceByFoodName");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, foodDto1.getItem_name());
            rset = pstmt.executeQuery();

            if(rset.next()) {
                foodDto = new FoodDto();
                foodDto.setItem_price(rset.getInt("ITEMPRICE"));
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            close(rset);
            close(pstmt);
        }


        return foodDto;

    }

}
