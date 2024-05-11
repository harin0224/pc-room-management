package member.view;

import member.controller.MemberController;

import java.util.Scanner;

public class Member {
    static MemberController memberController = new MemberController();
    //  회원 가입
    public static void signUp(){
        Scanner scan = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.println("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();
        // memberController.searchMember(id, password);
    }

}
