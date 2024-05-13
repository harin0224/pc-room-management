package member.service;

import member.model.dao.MemberDao;
import member.model.dto.MemberDto;

public class MemberService {
    MemberDao memberDao = null;

    public MemberService() {
        memberDao = new MemberDao();
    }

    public void addMember(MemberDto memberDto) {
        memberDao.addMember(memberDto);
    }

    public void deleteMember(String id) {
        memberDao.deleteMember(id);
    }

    public boolean searchMember(MemberDto memberDto) {
        return memberDao.searchMember(memberDto);
    }

    public void myPage() {
        memberDao.myPage();
    }

    public void updateMember(int changeType, String changeContent) {
        memberDao.updateMember(changeType, changeContent);
    }

    // 사용 시간 수정(로그아웃)
    public void signOut(){
        System.out.println("로그아웃 되었습니다.");
        // currentId = null;   // 아이디 비우기
        System.exit(0); // 프로그램 종료

    }
}
