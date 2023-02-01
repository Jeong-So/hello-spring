package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller 사용하면 스프링컨테이너가 뜰때 MemberController를 생성하여 스프링에서 직접 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    // @Autowired : 스프링컨테이너가 떠서 MemberController를 생성할 때 생성자를 호출
    // memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    1. 필드 주입
//    @Autowired private MemberService memberService;

//    2. 생성자 주입 : 생성자를 통해서 memberService가 MemberController에 주입
    // @Autowired : 스프링컨테이너가 떠서 MemberController를 생성할 때 생성자를 호출
    // memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다가 연결을 시켜줌
//    @Autowired
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

//    3. setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

}
