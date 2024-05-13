package member.view;

import member.controller.MemberController;

import java.util.Scanner;

public class Member {
    static MemberController memberController = new MemberController();
    static Scanner scan = new Scanner(System.in);
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
        String password = scan.nextLine();
        // memberController.searchMember(id, password);
    }
    // 회원 탈퇴
    public static void DeleteMember(){
        //Scanner scan = new Scanner(System.in);
        System.out.println("ID를 입력하세요: ");
        String id = scan.nextLine();
        System.out.println("비밀번호를 입력하세요: ");
        String password = scan.nextLine();
        memberController.deleteMember(id, password);
    }
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

}
