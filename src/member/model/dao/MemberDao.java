package member.model.dao;

import common.DBConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static common.DBConnection.getConnection;

public class MemberDao {
    private Properties prop = null;
    private Connection conn = null;

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
            rset = stmt.executeQuery(dupIdSql);

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
    public void deleteMember(String id, String pw, String name){


    }

    // 회원 조회(로그인)
    public void searchMember(String id, String pw){

    }
    
    // 회원 조회 및 수정(마이페이지)
    public void myPage(String id, String pw){

    }

    // 사용 시간 수정
    public void updateTime(String id, int time){

    }
    
}
