package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

//@Controller 애노테이션 ;
// 애노테이션 명시하면 스프링 컨테이네에 객체가 자동으로 생성되고
// 스프링이 관리해줌
/*
#의존성 주입
new 로 선언하기 보다는 스프링이 객체를 생성하고 관리하도록 지정
객체를 한개만 생성하고 다른 클래스(?)에서도 공유해 사용할 수 있음
 */
@Controller
public class MemberController {
    //의존성 주입 방법; 1) setter 주입, 2) 생성자 주입
    //생성자 주입; 스프링 주입하는 방법으로 생성자를 통한 주입을 추천함
    //생성하는 시점에만 빈끼리 조립하는 단계에서 끝낼 수 있음
    //실행 중 의존관계가 변동되는 일은 희박하므로, setter로 메서드 접근을 허용할 이유가 없음

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    //스프링 컨테이너에 등록하는 작업
    //방법; 필드주입
    @Autowired
    MemberService memberService;

    //방법; 생성자 주입
    //생성자에 애노테이션을 입력하여 스프링객체로서 주입하기
    //개발자가 넣지 않고 스프링 실행시 자동으로 주입됨
    //autowired
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* url 매핑 설정 */
    @GetMapping("/members/new")
    public String createForm(){
        //template 폴더에서 리턴값에 해당하는 뷰를 찾는다
        return "members/createMemberForm";
    }

    //form 등으로 요청시 매핑됨
    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
