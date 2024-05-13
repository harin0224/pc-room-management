package member.controller;

import member.model.dao.MemberDao;
import member.model.dto.MemberDto;
import member.service.MemberService;

public class MemberController {
    MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }

    public void addMember(MemberDto memberDto) {
        memberService.addMember(memberDto);
    }

    public void deleteMember(String id) {
        memberService.deleteMember(id);
    }

    public boolean searchMember(MemberDto memberDto) {
        return memberService.searchMember(memberDto);
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
