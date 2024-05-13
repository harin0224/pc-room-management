package member.view;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import member.controller.MemberController;

import java.util.Objects;
import java.util.Scanner;

public class Member {
    static MemberController memberController = new MemberController();
    static Scanner scan = new Scanner(System.in);
    static String currentId = null; // 로그아웃시 초기화해야함
    //  회원 가입
    public static void signUp(){
        System.out.println("ID를 입력하세요: ");
        String id = scan.nextLine();
        System.out.println("PW를 입력하세요: ");
        String pw = scan.nextLine();
        System.out.println("이름을 입력하세요: ");
        String name = scan.nextLine();
        System.out.println("전화번호를 입력하세요: ");
        String number = scan.nextLine();
        memberController.addMember(id, pw, name, number);
    }

    // 로그인
    public static void signIn() {
        // Scanner scan = new Scanner(System.in);
        System.out.println("ID를 입력하세요: ");
        String id = scan.nextLine();
        System.out.println("비밀번호를 입력하세요: ");
        String pw = scan.nextLine();
        memberController.searchMember(id, pw);
        currentId = id;
    }
    // 회원 탈퇴
//    public static void DeleteMember(){
//        //Scanner scan = new Scanner(System.in);
//        System.out.println("ID를 입력하세요: ");
//        String id = scan.nextLine();
//        System.out.println("비밀번호를 입력하세요: ");
//        String pw = scan.nextLine();
//        memberController.deleteMember(id);
//    }
    public static void UpdateMember(){
        System.out.println("수정할 정보를 선택하세요: ");
        System.out.println("1. 비밀번호");
        System.out.println("2. 이름");
        System.out.println("3. 전화번호");
        System.out.println("+ 아이디는 변경할 수 없습니다.");
        int changeType = scan.nextInt();
        String changeContent = null;
        switch (changeType) {
            case 1:
                System.out.println("변경할 비밀번호를 입력하세요: ");
                changeContent = scan.nextLine();
                break;
            case 2:
                System.out.println("변경할 이름을 입력하세요: ");
                changeContent = scan.nextLine();
                break;
            case 3:
                System.out.println("변경할 전화번호를 입력하세요: ");
                changeContent = scan.nextLine();
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
                break;
        }
        memberController.updateMember(changeType, changeContent);
    }
    public  static void myPage(){
        System.out.println("마이페이지입니다.");
        System.out.println("메뉴를 선택하세요.");
        System.out.println("1. 시간 충전");
        System.out.println("2. 내 정보");
        System.out.println("3. 회원 탈퇴");
        boolean isValidInput = false;
        int menu = scan.nextInt();
        do {
            switch (menu) {
                case 1:

                    isValidInput = true;
                    break;
                case 2:
                    memberController.myPage();
                    System.out.println("메뉴를 선택하세요.");
                    System.out.println("1. 내 정보 수정");
                    System.out.println("2. 돌아가기");
                    if(Objects.equals(scan.nextLine(), "1")){
                        UpdateMember();
                    }else{
                        isValidInput = true;
                    }

                    break;
                case 3:
                    System.out.println("탈퇴하시겠습니까?");
                    System.out.println("1. Y");
                    System.out.println("2. N");
                    if (Objects.equals(scan.nextLine(), "Y")) {
                        memberController.deleteMember(currentId);
                        isValidInput = true;
                    } else if (Objects.equals(scan.nextLine(), "N")) {
                        System.out.println("취소하셨습니다.");
                    } else {
                        System.out.println("잘못 입력하셨습니다.");
                    }
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
        }while (!isValidInput);


        
        



    }

}
