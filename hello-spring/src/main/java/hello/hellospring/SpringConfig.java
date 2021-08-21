package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바코드로 스프링 빈 등록하기
//자바코드로 연결하면 설정하는 작업은 애노테이션보다 간단하지 않지만
//유지보수가 간편한 것이 장점이다
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

//    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
