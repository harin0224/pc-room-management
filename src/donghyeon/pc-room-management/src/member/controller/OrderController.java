package member.controller;

import member.model.dao.FoodDao;
import member.model.dao.OrderDao;
import member.model.dto.FoodDto;
import member.model.dto.OrderDto;
import member.service.FoodService;
import member.service.OrderService;
import member.view.AdminMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderController {

    private OrderService orderService = new OrderService();
    Scanner scanner = new Scanner(System.in);

    public void orderManage() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<주문 처리>");
        System.out.println("1.접수된주문   2.완료된주문   0.이전화면");

        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 0:
                new AdminMenu().mainMenu();
                break;
            case 1:
                orderCurrent();
                break;
            case 2:
                orderCompleted();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                orderManage();
                break;
        }
    }

    private void orderCurrent() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<현재 접수된 주문 목록>");

        ////////////////////////////////////////////////////////////////////////

        ArrayList<OrderDto> list;


        list = orderService.selectByCurrentOrder();
        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'

        if(!list.isEmpty()) {
            System.out.println("\n조회된 전체 음식메뉴 정보는 다음과 같습니다.");
            System.out.println("\n상품번호\t상품명\t가격");
            System.out.println("주문번호 / 주문시간 / 회원아이디 / 음식이름 / 개수 / 총가격");
            System.out.println("*********************************************************");

            for(OrderDto o: list) {
                System.out.println(o);
            }

            System.out.println("*********************************************************");
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

        ///////////////////////////////////////////////////////////////////////

        System.out.println();
        System.out.println("1.서빙완료처리    0.이전화면");
        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        if (choice == 0) {
            orderManage();
        } else if (choice == 1) {
            orderServing();
        } else {
            System.out.println("잘못된 입력입니다.");
            orderCurrent();
        }

//        switch (choice) {
//            case 0:
//                orderManage();
//                break;
//            case 1:
//                orderServing();
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//                orderCurrent();
//                break;
//        }

    }

    private void orderServing() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<주문 서빙완료 처리>");
        System.out.print("서빙완료처리할 주문번호를 선택 >>> ");
        choice = scanner.nextInt();

        ////////////////////////////////////////////////////////////////////////

        // 주문 서빙완료여부 수정
        //1. 입력한 값 받기
        //2. DTO 에 값 담기
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_no(choice);

        //3. 다오객체 생성해서 update 만들어서 전달하기
        OrderDao orderDao = new OrderDao();
        int result = orderDao.updateServing(orderDto);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("서빙완료 수정 성공!!");
        }else{
            System.out.println("서빙완료 수정 실패!!");
        }

        ///////////////////////////////////////////////////////////////////////

        orderManage();

    }

    private void orderCompleted() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<완료된 주문 목록>");


        ////////////////////////////////////////////////////////////////////////


        ArrayList<OrderDto> list;


        list = orderService.selectByCompletedOrder();
        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'

        if(!list.isEmpty()) {
            System.out.println("\n조회된 전체 음식메뉴 정보는 다음과 같습니다.");
            System.out.println("\n상품번호\t상품명\t가격");
            System.out.println("주문번호 / 주문시간 / 회원아이디 / 음식이름 / 개수 / 총가격");
            System.out.println("*********************************************************");

            for(OrderDto o: list) {
                System.out.println(o);
            }

            System.out.println("*********************************************************");
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

        ///////////////////////////////////////////////////////////////////////


        System.out.println("0. 이전화면");
        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 0:
                orderManage();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                orderCompleted();
                break;
        }
    }

    public void displayOrderList(List<OrderDto> list) {
        System.out.println("\n조회된 전체 주문 정보는 다음과 같습니다.");
        System.out.println("\n주문번호\t주문시간\t회원아이디\t상품명\t개수\t총가격\t서빙완료여부");
        System.out.println("----------------------------------------------------------");

        for(OrderDto o: list) {
            System.out.println(o);
        }

    }

    public void displayNoData() {
        System.out.println("조회된 데이터가 없습니다.");
    }

}
