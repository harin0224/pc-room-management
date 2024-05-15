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

    public void adminLogin(String id, String pw) {
        memberDao.adminLogin(id, pw);
    }

    public void modifyMember(String id, String pw, String name, String number) {
        memberDao.modifyMember(id, pw, name, number);
    }

    public void delete(String id) {
        memberDao.delete(id);
    }

    public void memberList() {
        memberDao.memberList();
    }
}