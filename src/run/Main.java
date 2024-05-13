package run;

import member.view.Member;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PC방에 오신 것을 환영합니다.");
        System.out.println("메뉴를 선택하세요");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("로그인");
                Member.signIn();
                System.out.println("로그인이 완료되었습니다.");
                System.out.println("메뉴를 선택하세요.");
                System.out.println("1. 마이페이지");
                System.out.println("2. 음식 주문 관리");
                System.out.println("3. 로그아웃");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("마이페이지");
                        Member.myPage();
                        break;
                    case 2:
                        System.out.println("음식 주문 관리");
                        // 주문
                        break;
                    case 3:
                        System.out.println("로그아웃");
                        // 로그 아웃
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
                break;
            case 2:
                System.out.println("회원가입");
                Member.signUp();
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }
}