package member.model.dao;

import common.DBConnection;
import member.model.dto.MemberDto;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

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
    public void addMember(MemberDto memberDto) {
        // Statement stmt = null;// 실행할 쿼리
        ResultSet rset = null;// Select 한후 결과값 받아올 객체

        // 중복 아이디 확인
        String dupIdSql = prop.getProperty("selectById");
        try {
            PreparedStatement dupIdPs = conn.prepareStatement(dupIdSql);
            dupIdPs.setString(1, memberDto.getId());
            // stmt = conn.createStatement();
            rset = dupIdPs.executeQuery();


            if(rset.next()) {   // 중복이라면
                System.out.println("이미 존재하는 아이디입니다.");
            }else { // 중복이 아니라면 회원 추가
                String createMemberSql = prop.getProperty("createMember");
                PreparedStatement createMemberPs = conn.prepareStatement(createMemberSql);
                createMemberPs.setString(1, memberDto.getId());
                createMemberPs.setString(2, memberDto.getPw());
                createMemberPs.setString(3, memberDto.getName());
                createMemberPs.setString(4, memberDto.getNumber());
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
    public boolean searchMember(MemberDto memberDto){
        String SignInSql = prop.getProperty("selectByIdPw");
        try {
            PreparedStatement SignInPs = conn.prepareStatement(SignInSql);
            SignInPs.setString(1, memberDto.getId());
            SignInPs.setString(2, memberDto.getPw());
            ResultSet rset = SignInPs.executeQuery();
            if(rset.next()) {
                System.out.println("로그인 성공");
                // 로그인 시간 기록
                LocalDateTime loginTime = LocalDateTime.now();
                currentId = memberDto.getId();
                // 로그인 성공 후 남은 시간 갱신 태스크 생성
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        // 현재 시간 기록
                        LocalDateTime currentTime = LocalDateTime.now();
                        // 경과 시간 계산
                        Duration duration = Duration.between(loginTime, currentTime);
                        long useTime = duration.getSeconds();

                        // 남은 시간 alert 해주는 함수
                        alertRemainingTime(useTime);
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

    private void alertRemainingTime(long useTime) {
        Scanner scanner = new Scanner(System.in);
        // 남은 시간 갱신 로직 (가정)
        int remainingTime = getRemainingTime();
        remainingTime -= 60;
        // System.out.println("remainingTime: " + remainingTime + "useTime: " + useTime);
        PreparedStatement updateTimePs = null;
        String updateTimeSql = prop.getProperty("updateTime");
        try {
            updateTimePs = conn.prepareStatement(updateTimeSql);
            updateTimePs.setInt(1, remainingTime);
            updateTimePs.setString(2, currentId);
            updateTimePs.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        if (remainingTime <= 0) {
            // 남은 시간이 0 이하이면 타이머 종료 및 프로그램 종료
            timer.cancel();
            System.out.println("남은 시간이 모두 소진되었습니다.");
            System.exit(0);

//            System.out.println("1. Y");
//            System.out.println("2. N");
//            scanner.nextLine();

//            String charge = scanner.nextLine();
//            if(charge.equals("1")) {
//                // 충전하는 메소드 들어감
//            }else {
//                System.out.println("프로그램을 종료합니다.");
//            }
//            System.exit(0);

        } else {
            // 남은 시간 alert
            System.out.println(remainingTime / 60 + "분 남았습니다.");
        }
    }
    // 사용자의 남은 시간을 데이터베이스에서 조회하는 메서드
    private int getRemainingTime() {
        PreparedStatement getTimePs = null; // 실행할 쿼리
        ResultSet rset = null; // Select 한후 결과값 받아올 객체
        String getTimeSql = prop.getProperty("selectTime");
        try{
            getTimePs = conn.prepareStatement(getTimeSql);
            getTimePs.setString(1, currentId);
            rset = getTimePs.executeQuery();
            if(rset.next()) {
                return rset.getInt("time");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }return 0;
    }
    
    // 내 정보
    public void myPage() {
        PreparedStatement myPagePs = null; // 실행할 쿼리
        ResultSet rset = null; // Select 한후 결과값 받아올 객체
        String myPageSql = prop.getProperty("selectById");

        try {
            myPagePs = conn.prepareStatement(myPageSql);
            myPagePs.setString(1, currentId);
            rset = myPagePs.executeQuery();
            while (rset.next()) {
                String id = rset.getString("id");
                String name = rset.getString("name");
                String num = rset.getString("num");
                int time = rset.getInt("time");

                // 가져온 데이터 출력 또는 처리
                System.out.println("ID: " + id);
                System.out.println("이름: " + name);
                System.out.println("번호: " + num);
                System.out.println("사용 시간: " + time);
            }

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



}
