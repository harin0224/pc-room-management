package member.controller;

import member.service.MemberService;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void addMember(String id, String pw, String name, String number) {
        memberService.addMember(id, pw, name, number);
    }

    public void adminLogin(String id, String pw) {
        memberService.adminLogin(id, pw);
    }

    public void modifyMember(String id, String pw, String name, String number) {
        memberService.modifyMember(id, pw, name, number);
    }

    public void delete(String id) {
        memberService.delete(id);
    }

    public void memberList(){
        memberService.memberList();
    }
}