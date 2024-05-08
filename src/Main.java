import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("PC방에 오신 것을 환영합니다.");
        System.out.println("메뉴를 선택하세요");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 로그아웃");

        switch (scanner.nextInt()) {
            case 1:
                System.out.println("로그인");
                break;
            case 2:
                System.out.println("회원가입");
                break;
            case 3:
                System.out.println("로그아웃");
                break;
            default:
                System.out.println("잘못된 입력입니다.");
                break;
        }

    }
}