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
}
