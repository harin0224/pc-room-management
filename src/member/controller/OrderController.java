package member.controller;

import member.model.dao.FoodDao;
import member.model.dao.OrderDao;
import member.model.dto.FoodDto;
import member.model.dto.OrderDto;
import member.service.FoodService;
import member.service.OrderService;
import member.view.AdminMenu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {

    private OrderService orderService = new OrderService();
    Scanner scanner = new Scanner(System.in);


    public ArrayList<OrderDto> selectByCurrentOrder() {
        ArrayList<OrderDto> list;
        list = orderService.selectByCurrentOrder();
        return list;
    }

    public int updateServing(OrderDto orderDto) {
        int result;
        result = orderService.updateServing(orderDto);
        return result;
    }

    public ArrayList<OrderDto> selectByCompletedOrder() {
        ArrayList<OrderDto> list;
        list = orderService.selectByCompletedOrder();
        return list;
    }
}
