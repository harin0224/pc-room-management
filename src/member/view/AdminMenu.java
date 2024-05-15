package member.view;

import member.controller.FoodController;
import member.controller.FoodOrderController;
import member.controller.OrderController;
import member.model.dao.FoodDao;
import member.model.dao.OrderDao;
import member.model.dto.FoodDto;
import member.model.dto.OrderDto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static Scanner scanner = new Scanner(System.in);
    private FoodController foodController = new FoodController();
    private OrderController orderController = new OrderController();
    private FoodOrderController foodOrderController = new FoodOrderController();
    static String currentId = null;


    public void mainMenu(){ // 관리자 화면 - 동현 작성
        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<관리자 화면>");
        System.out.println("1.회원관리  2.음식메뉴관리   3.주문처리   0.이전화면");
        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();


        switch (choice) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                foodManage();
                break;
            case 3:
                orderManage();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                mainMenu();
                break;
        }

    }


    public void foodManage() { // 음식메뉴 관리 선택 화면

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 관리>");
        System.out.println("1.열람   2.추가   3.수정   4.삭제   0.이전화면");

        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 0:
                mainMenu();
                break;
            case 1:
                selectAllFood();
                break;
            case 2:
                insertFood();
                break;
            case 3:
                updateFood();
                break;
            case 4:
                deleteFood();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                foodManage();
                break;
        }
    }


    private void selectAllFood() { // 음식메뉴 열람

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 열람>");

        ArrayList<FoodDto> list;

        list = foodController.selectAllFood();

        displayFoodList(list);

        foodManage();
    }


    private void insertFood() { // 음식메뉴 추가


        String foodName;
        int price;

        // 입력한 값 받기
        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 추가>");
        System.out.print("음식이름 : ");
        foodName = scanner.next();
        System.out.print("가격 : ");
        price = scanner.nextInt();

        // DTO 에 값 담기
        FoodDto foodDto = new FoodDto();
        foodDto.setItem_name(foodName);
        foodDto.setItem_price(price);

        // DTO 객체 컨트롤러에 전달하기
        int result = foodController.insertFood(foodDto);

        // result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴추가 성공!!");
        }else{
            System.out.println("메뉴추가 실패!!");
        }

        foodManage();

    }


    private void updateFood() { // 음식메뉴 수정

        String foodName1;
        String foodName2;
        int price2;

        // 입력한 값 받기
        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 수정>");
        System.out.print("수정할 음식이름 : ");
        foodName1 = scanner.next();
        System.out.println("(수정을 시작하겠습니다)");
        System.out.print("음식이름 : ");
        foodName2 = scanner.next();
        System.out.print("가격 : ");
        price2 = scanner.nextInt();

        // DTO 에 값 담기
        FoodDto foodDto1 = new FoodDto();
        FoodDto foodDto2 = new FoodDto();
        foodDto1.setItem_name(foodName1);
        foodDto2.setItem_name(foodName2);
        foodDto2.setItem_price(price2);

        // DTO 객체 컨트롤러에 전달하기
        int result = foodController.updateFood(foodDto1,foodDto2);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴 수정 성공!!");
        }else{
            System.out.println("메뉴 수정 실패!!");
        }

        foodManage();
    }


    private void deleteFood() { // 음식메뉴 삭제

        String foodName;

        // 입력한 값 받기
        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 삭제>");
        System.out.print("삭제할 음식이름 : ");
        foodName = scanner.next();

        // DTO 에 값 담기
        FoodDto foodDto = new FoodDto();
        foodDto.setItem_name(foodName);

        // DTO 객체 컨트롤러에 전달하기
        int result = foodController.deleteFood(foodDto);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴 삭제 성공!!");
        }else{
            System.out.println("메뉴 삭제 실패!!");
        }

        foodManage();
    }


    public void orderManage() { // 주문 처리 화면

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<주문 처리>");
        System.out.println("1.접수된주문   2.완료된주문   0.이전화면");

        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 0:
                mainMenu();
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


    private void orderCurrent() { // 현재 접수된 주문 목록

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<현재 접수된 주문 목록>");

        ArrayList<OrderDto> list;

        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'
        list = orderController.selectByCurrentOrder();


        if(!list.isEmpty()) {
            displayOrderList(list);
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

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

    }


    private void orderServing() { // 주문 서빙 완료 처리

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<주문 서빙완료 처리>");
        System.out.print("서빙완료처리할 주문번호를 선택 >>> ");
        choice = scanner.nextInt();

        //2. DTO 에 값 담기
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder_no(choice);

        // DTO 객체 컨트롤러에 전달하기
        int result = orderController.updateServing(orderDto);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("서빙완료 수정 성공!!");
        }else{
            System.out.println("서빙완료 수정 실패!!");
        }

        orderManage();

    }


    private void orderCompleted() { // 완료된 주문 목록

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<완료된 주문 목록>");

        ArrayList<OrderDto> list;

        //selectByCompletedOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'y'
        //selectByCurrentOrder=SELECT * FROM TBL_ORDER WHERE SERVING = 'n'
        list = orderController.selectByCompletedOrder();

        if(!list.isEmpty()) {
            displayOrderList(list);
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

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

    public void displayFoodList(List<FoodDto> list) {

        if(!list.isEmpty()) {
            System.out.println("\n상품번호  /  상품명  /  가격");
            System.out.println("*********************************************************");

            for(FoodDto f: list) {
                System.out.println(f);
            }

            System.out.println("*********************************************************");
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

    }

    public void displayOrderList(List<OrderDto> list) {

        System.out.println("\n주문번호  /  주문시간  /  회원아이디  /  음식이름  /  개수  /  총가격  /  서빙완료여부");
        System.out.println("*********************************************************");

        for(OrderDto o: list) {
            System.out.println(o);
        }

        System.out.println("*********************************************************");

    }

    //////////////////////////////////////////////////////////////////////
    //////정헌님 주문하기, 주문내역 확인 메소드
    public void foodOrder() { // 음식메뉴 추가 -> 1.메뉴리스트 호출 2. 주문받기

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 주문>");

        // 1. 메뉴리스트 호출(시작)
        int choice;

        ArrayList<FoodDto> list;

        list = foodController.selectAllFood();

        displayFoodList(list);
        // 1. 메뉴리스트 호출(끝)

        // 2. 주문받기(시작)
        String foodName;
        int cnt;

        // 입력한 값 받기
        System.out.print("주문할 음식이름 : ");
        foodName = scanner.next();
        System.out.print("주문할 개수 : ");
        cnt = scanner.nextInt();

        // DTO에 담을 total_price 가져오기
        // food테이블에서 음식이름으로 검색해서 맞는 음식가격itemprice를 가져와야함
        FoodDto foodDto = null;
        //selectFoodPriceByFoodName()메소드로 음식가격을 담은 DTO를 가져옴
        foodDto = selectFoodPriceByFoodName(foodName);
        int foodPrice = foodDto.getItem_price();
        int totalPrice = foodPrice * cnt;


        // DTO 에 값 담기
        OrderDto orderDto = new OrderDto();
        //orderDto.setUser_id(Member.currentId);
        orderDto.setUser_id("임시값 변경필수"); // currentId는 View - AdminMenu, Memeber 클래스에 전역변수로 선언되어있음.
        orderDto.setItem_name(foodName);
        orderDto.setCnt(cnt);
        orderDto.setTotal_price(totalPrice);
        //        private int order_no;
        //        private Date order_time;
        //        private String user_id;
        //        private String item_name;
        //        private int cnt;
        //        private int total_price;
        //        private String serving;


        // DTO 객체 컨트롤러에 전달하기
        int result = foodOrderController.foodOrder(orderDto);

        // result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("주문 성공!!");
        }else{
            System.out.println("주문 실패!!");
        }

        //foodManage();

    }

    // 음식이름을 입력받으면 , 대응되는 음식가격을 담은 DTO를 반환하는 메소드
    private FoodDto selectFoodPriceByFoodName(String item_name) {
        //foodDto1 은 음식이름을 담아서 Dao까지 보냄
        FoodDto foodDto1 = new FoodDto();
        foodDto1.setItem_name(item_name);

        //foodDto2 는 Dao에서부터 음식가격을 가져옴.
        FoodDto foodDto2 = null;
        foodDto2 = foodOrderController.selectFoodPriceByFoodName(foodDto1);
        return foodDto2;
    }


    private void foodOrderCheck() { // 음식메뉴 열람

    }




}
