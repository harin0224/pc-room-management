package member.controller;

import member.model.dao.FoodDao;
import member.model.dto.FoodDto;
import member.service.FoodService;
import member.view.AdminMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodController {

    private FoodService foodService = new FoodService();


    public ArrayList<FoodDto> selectAllFood() {
        ArrayList<FoodDto> list = foodService.selectAllFood();
        return list;
    }

    public int insertFood(FoodDto foodDto) {

        int result = foodService.insertFood(foodDto);

        return result;
    }

    public int updateFood(FoodDto foodDto1, FoodDto foodDto2) {

        int result = foodService.updateFood(foodDto1, foodDto2);

        return result;
    }

    public int deleteFood(FoodDto foodDto) {

        int result = foodService.deleteFood(foodDto);

        return result;
    }


}


