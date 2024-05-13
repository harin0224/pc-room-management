package member.service;

import member.model.dao.FoodDao;
import member.model.dto.FoodDto;

import java.sql.Connection;
import java.util.ArrayList;

import static common.DBConnection.getConnection;
/* Service 클래스에서 메소드 작성 방법
 * 1) Controller로 부터 인자를 전달받음
 * 2) Connection 객체 생성
 * 3) Dao 객체 생성
 * 4) Dao로 생성한 Connection 객체와 인자를 전달
 * 5) Dao 수행 결과를 가지고 비즈니스 로직 및 트랜잭션 관리를 함 */

public class FoodService {

    private final FoodDao foodDao;

    public FoodService() { foodDao = new FoodDao();  }

    public ArrayList<FoodDto> selectAllFood() {

        Connection conn = getConnection();
        ArrayList<FoodDto> list = foodDao.selectAllFood(conn);

        return list;

    }

}
