package member.controller;

import member.model.dao.MemberDao;
import member.service.MemberService;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void addMember(String id, String pw, String name, String number) {
        memberService.addMember(id, pw, name, number);
    }

    public void deleteMember(String id) {
        memberService.deleteMember(id);
    }

    public void searchMember(String id, String pw) {
        memberService.searchMember(id, pw);
    }

    public void myPage() {
        memberService.myPage();
    }

    public void updateMember(int changeType, String changeContent) {
        memberService.updateMember(changeType, changeContent);
    }

    public void signOut(){
        memberService.signOut();
    }
}
