package member.view;

import member.controller.FoodController;
import member.controller.OrderController;

import java.util.Scanner;

public class AdminMenu {

    private static Scanner scanner = new Scanner(System.in);
    //private MemberController memberController = new MemberController();
    private FoodController foodController = new FoodController();
    private OrderController orderController = new OrderController();


    public void mainMenu(){
        int choice;

        System.out.println("----------------------------------------------------------------");
        //System.out.println("관리자로 로그인 되었습니다.");
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
                foodController.foodManage();
                break;
            case 3:
                orderController.orderManage();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                new AdminMenu().mainMenu();
                break;
        }

    }

//    public void displayFoodList(List<FoodDto> list) {
//        System.out.println("\n조회된 전체 음식메뉴 정보는 다음과 같습니다.");
//        System.out.println("\n상품번호\t상품명\t가격");
//        System.out.println("----------------------------------------------------------");
//
//        for(FoodDto f: list) {
//            System.out.println(f);
//        }
//
//    }
//
//    public void displayOrderList(List<OrderDto> list) {
//        System.out.println("\n조회된 전체 주문 정보는 다음과 같습니다.");
//        System.out.println("\n주문번호\t주문시간\t회원아이디\t상품명\t개수\t총가격\t서빙완료여부");
//        System.out.println("----------------------------------------------------------");
//
//        for(OrderDto o: list) {
//            System.out.println(o);
//        }
//
//    }
//
//    public void displayNoData() {
//        System.out.println("조회된 데이터가 없습니다.");
//    }
}
