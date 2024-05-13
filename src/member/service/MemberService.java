package member.service;

import member.model.dao.MemberDao;

public class MemberService {
    MemberDao memberDao = null;

    public MemberService() {
        memberDao = new MemberDao();
    }

    public void addMember(String id, String pw, String name, String number) {
        memberDao.addMember(id, pw, name, number);
    }

    public void deleteMember(String id) {
        memberDao.deleteMember(id);
    }

    public boolean searchMember(String id, String pw) {
        return memberDao.searchMember(id, pw);
    }

    public void myPage() {
        memberDao.myPage();
    }

    public void updateMember(int changeType, String changeContent) {
        memberDao.updateMember(changeType, changeContent);
    }

    public void signOut() {
        memberDao.signOut();
    }
}
