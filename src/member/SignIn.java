package member;

import java.util.Scanner;

public class SignIn {
    public void signIn(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID를 입력하세요: ");
        String id = scanner.nextLine();
        
        System.out.println("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        // scanner.close();
        // db에서 유저 찾기

        if(true){

        }else {
            System.out.println("ID 또는 비밀번호가 잘못되었습니다.");
            return;
        }
    }
}
