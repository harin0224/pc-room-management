package member.service;

import member.model.dao.FoodDao;
import member.model.dao.FoodOrderDao;
import member.model.dto.FoodDto;
import member.model.dto.OrderDto;

import java.sql.Connection;

import static common.DBConnection.*;

public class FoodOrderService {

    private final FoodOrderDao foodOrderDao;

    public FoodOrderService() { foodOrderDao = new FoodOrderDao();  }

    public FoodDto selectFoodPriceByFoodName(FoodDto foodDto1) {

        Connection conn = getConnection();

        //foodDto1 은 음식이름을 담아서 Dao까지 보냄
        //foodDto2 는 Dao에서부터 음식가격을 가져옴.
        FoodDto foodDto2 = null;
        foodDto2 = foodOrderDao.selectFoodPriceByFoodName(conn, foodDto1);
        return foodDto2;
    }

    public int foodOrder(OrderDto orderDto) {
        Connection conn = getConnection();
        int result = foodOrderDao.foodOrder(conn, orderDto);

        if(result > 0) commit(conn);
        else rollback(conn);

        return result;
    }
}
