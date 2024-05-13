package member.controller;

import member.model.dao.FoodDao;
import member.model.dto.FoodDto;
import member.service.FoodService;
import member.view.AdminMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodController {

    private FoodService foodService = new FoodService();
    Scanner scanner = new Scanner(System.in);

    public void foodManage() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 관리>");
        System.out.println("1.열람   2.추가   3.수정   4.삭제   0.이전화면");

        System.out.print("번호선택 >>> ");
        choice = scanner.nextInt();

        switch (choice) {
            case 0:
                new AdminMenu().mainMenu();
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

    private void selectAllFood() {

        int choice;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 열람>");

        ////////////////////////////////////////////////////////////////////////


        ArrayList<FoodDto> list;

        list = foodService.selectAllFood();

        if(!list.isEmpty()) {
            System.out.println("\n조회된 전체 음식메뉴 정보는 다음과 같습니다.");
            System.out.println("\n상품번호\t상품명\t가격");
            System.out.println("*********************************************************");

            for(FoodDto f: list) {
                System.out.println(f);
            }

            System.out.println("*********************************************************");
        }else{
            System.out.println("조회된 데이터가 없습니다.");
        }

        ///////////////////////////////////////////////////////////////////////

//        System.out.println("0. 이전화면");
//        System.out.print("번호선택 >>> ");
//        choice = scanner.nextInt();
//
//        switch (choice) {
//            case 0:
//                foodManage();
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//                selectAllFood();
//                break;
//        }

        foodManage();
    }

    private void insertFood() {

        String foodName;
        int price;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 추가>");
        System.out.print("음식이름 : ");
        foodName = scanner.next();
        System.out.print("가격 : ");
        price = scanner.nextInt();
        ////////////////////////////////////////////////////////////////////////

        // 음식메뉴 추가
        //1. 입력한 값 받기
        //2. DTO 에 값 담기
        FoodDto foodDto = new FoodDto();
        foodDto.setItem_name(foodName);
        foodDto.setItem_price(price);

        //3. 다오객체 생성해서 INSERT 만들어서 전달하기
        FoodDao foodDao = new FoodDao();
        int result = foodDao.insertFood(foodDto);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴추가 성공!!");
            System.out.println("음식메뉴 \""+ foodName + "\"가 추가되었습니다!");
        }else{
            System.out.println("메뉴추가 실패!!");
        }

        ///////////////////////////////////////////////////////////////////////

        foodManage();


    }

    private void updateFood() {

        String foodName1;
        String foodName2;
        int price2;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 수정>");
        System.out.print("수정할 음식이름 : ");
        foodName1 = scanner.next();
        System.out.println("(수정을 시작하겠습니다)");
        System.out.print("음식이름 : ");
        foodName2 = scanner.next();
        System.out.print("가격 : ");
        price2 = scanner.nextInt();

        ////////////////////////////////////////////////////////////////////////

        // 음식메뉴 수정
        //1. 입력한 값 받기
        //2. DTO 에 값 담기
        FoodDto foodDto1 = new FoodDto();
        FoodDto foodDto2 = new FoodDto();
        foodDto1.setItem_name(foodName1);
        foodDto2.setItem_name(foodName2);
        foodDto2.setItem_price(price2);

        //3. 다오객체 생성해서 update 만들어서 전달하기
        FoodDao foodDao = new FoodDao();
        int result = foodDao.updateFood(foodDto1,foodDto2);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴 수정 성공!!");
        }else{
            System.out.println("메뉴 수정 실패!!");
        }

        ///////////////////////////////////////////////////////////////////////

        foodManage();
    }

    private void deleteFood() {

        String foodName;

        System.out.println("----------------------------------------------------------------");
        System.out.println("<음식메뉴 삭제>");
        System.out.print("삭제할 음식이름 : ");
        foodName = scanner.next();

        ////////////////////////////////////////////////////////////////////////

        // 음식메뉴 삭제
        //1. 입력한 값 받기
        //2. DTO 에 값 담기
        FoodDto foodDto = new FoodDto();
        foodDto.setItem_name(foodName);

        //3. 다오객체 생성해서 delete 만들어서 전달하기
        FoodDao foodDao = new FoodDao();
        int result = foodDao.deleteFood(foodDto);

        //4. result 가 1 이면, 메뉴추가 성공이라고 띄우고, 메뉴추가 실패
        if(result == 1){
            System.out.println("메뉴 삭제 성공!!");
        }else{
            System.out.println("메뉴 삭제 실패!!");
        }

        ///////////////////////////////////////////////////////////////////////

        foodManage();
    }





}


