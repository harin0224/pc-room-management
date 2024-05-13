package member.model.dao;

import common.DBConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import static common.DBConnection.getConnection;

public class MemberDao {
    private Properties prop = null;
    private Connection conn = null;
    private static final long CHECK_INTERVAL = 60 * 1000;
    public String currentId = null;
    private Timer timer;

    public MemberDao() {
        try {
            prop = new Properties();
            prop.load(new FileReader("resources/memberQuery.properties"));
            conn = getConnection();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // 회원 추가(회원 가입)
    public void addMember(String id, String pw, String name, String number) {
        Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체

        // 중복 아이디 확인
        String dupIdSql = prop.getProperty("selectById");
        try {
            PreparedStatement dupIdPs = conn.prepareStatement(dupIdSql);
            dupIdPs.setString(1, id);
            stmt = conn.createStatement();
            rset = dupIdPs.executeQuery();


            if(rset.next()) {   // 중복이라면
                System.out.println("이미 존재하는 아이디입니다.");
            }else { // 중복이 아니라면 회원 추가
                String createMemberSql = prop.getProperty("createMember");
                PreparedStatement createMemberPs = conn.prepareStatement(createMemberSql);
                createMemberPs.setString(1, id);
                createMemberPs.setString(2, pw);
                createMemberPs.setString(3, name);
                createMemberPs.setString(4, number);
                createMemberPs.executeUpdate();
                System.out.println("회원가입이 완료되었습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 삭제(회원 탈퇴)
    public void deleteMember(String id) {

        String deleteMemSql = prop.getProperty("deleteMember");
        try {
            PreparedStatement deleteMemPs = conn.prepareStatement(deleteMemSql);
            deleteMemPs.setString(1, id);
            deleteMemPs.executeUpdate();
            System.out.println("회원 탈퇴가 완료되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 회원 조회(로그인)
    public boolean searchMember(String id, String pw){
        String SignInSql = prop.getProperty("selectByIdPw");
        try {
            PreparedStatement SignInPs = conn.prepareStatement(SignInSql);
            SignInPs.setString(1, id);
            SignInPs.setString(2, pw);
            ResultSet rset = SignInPs.executeQuery();
            if(rset.next()) {
                System.out.println("로그인 성공");
                // 로그인 시간 기록
                LocalDateTime loginTime = LocalDateTime.now();
                currentId = id;
                // 로그인 성공 후 남은 시간 갱신 태스크 생성
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        // 현재 시간 기록
                        LocalDateTime currentTime = LocalDateTime.now();
                        // 경과 시간 계산
                        Duration duration = Duration.between(loginTime, currentTime);
                        long elapsedTimeInSeconds = duration.getSeconds();

                        // 남은 시간 갱신
                        updateRemainingTime(elapsedTimeInSeconds);
                    }
                }, CHECK_INTERVAL, CHECK_INTERVAL);
                return true;


            }else {
                System.out.println("로그인 실패");
                System.exit(0);
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateRemainingTime(long elapsedTimeInSeconds) {
        // 남은 시간 갱신 로직 (가정)
        int remainingTime = getRemainingTimeFromDatabase(currentId);
        remainingTime -= elapsedTimeInSeconds;

        if (remainingTime <= 0) {
            // 남은 시간이 0 이하이면 타이머 종료 및 프로그램 종료
            timer.cancel();
            System.out.println("남은 시간이 모두 소진되었습니다. 프로그램을 종료합니다.");
            System.exit(0);
        } else {
            // 남은 시간 업데이트
            // 여기에 사용자의 남은 시간을 업데이트하는 로직 추가
            // 예: 데이터베이스 업데이트
            System.out.println("사용자 " + currentId + "의 남은 시간을 " + remainingTime + "초만큼 갱신했습니다.");
        }
    }
    // 사용자의 남은 시간을 데이터베이스에서 조회하는 메서드 (가정)
    private int getRemainingTimeFromDatabase(String userId) {
        // 여기에 사용자의 남은 시간을 데이터베이스에서 조회하는 로직 추가
        // 가정으로 임시 구현
        return 300; // 예시로 5분으로 설정
    }
    
    // 내 정보
    public void myPage() {
        Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체
        String myPageSql = prop.getProperty("selectById");

        try {
            PreparedStatement myPagePs = conn.prepareStatement(myPageSql);
            myPagePs.setString(1, currentId);
            stmt = conn.createStatement();
            rset = stmt.executeQuery(myPageSql);
            System.out.println(rset);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    // 마이페이지 내 회원 정보 수정
    public void updateMember(int changeType, String changeContent) {
        String updateMemberSql = null;
        switch (changeType) {
            case 1:
                updateMemberSql = prop.getProperty("updatePassword");
                break;
            case 2:
                updateMemberSql = prop.getProperty("updateName");
                break;
            case 3:
                updateMemberSql = prop.getProperty("updateNumber");
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
                break;
        }
        try {
            PreparedStatement updateMemberPs = conn.prepareStatement(updateMemberSql);
            updateMemberPs.setString(1, changeContent);
            updateMemberPs.setString(2, currentId);
            updateMemberPs.executeUpdate();
            System.out.println("회원 정보 수정이 완료되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // 사용 시간 수정(로그아웃)
    public void signOut(){
        // String SignOutSql = prop.getProperty("selectById");
        currentId = null;   // 아이디 비우기
        // timer.cancel();   // 타이머 종료 ?

    }

}
