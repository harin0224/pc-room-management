package member.controller;

import member.model.dto.FoodDto;
import member.model.dto.OrderDto;
import member.service.FoodOrderService;
import member.service.FoodService;

public class FoodOrderController {

    private FoodOrderService foodOrderService = new FoodOrderService();

    public FoodDto selectFoodPriceByFoodName(FoodDto foodDto1) {

        //foodDto1 은 음식이름을 담아서 Dao까지 보냄
        //foodDto2 는 Dao에서부터 음식가격을 가져옴.
        FoodDto foodDto2 = null;
        foodDto2 = foodOrderService.selectFoodPriceByFoodName(foodDto1);
        return foodDto2;
    }

    public int foodOrder(OrderDto orderDto) {
        int result = 0;
        result = foodOrderService.foodOrder(orderDto);
        return result;
    }
}
