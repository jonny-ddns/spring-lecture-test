package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class MemberController {
    //스프링 컨테이너에 등록하는 작업
    private final MemberService memberService;

//    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
