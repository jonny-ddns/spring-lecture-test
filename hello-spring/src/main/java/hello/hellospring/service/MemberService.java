package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

/*
repository 와 service 의 기능 분리
repository 는 데이터에 직접 접근하는 뭔가 단순한 기능
service 는 비즈니스 로직을 담는다. 좀더 로직다운 기능을 담는다
 */
//Service 애노테이션

//@Service
public class MemberService {


    /*
    --- DI 의존성 주입 ---
    @Autowired 애노테이션
    스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 주입해주는 기법
    애노테이션을 붙이면 개발자 작성한 코드가 아닌 스프링에서 알아서 주입해준다
    
    참고로 애노테이션은 주입되는 객체와 주입받는 객체, 양쪽에 명시해야함
    스프링 애노테이션이 없으면 순수한 자바클래스로 인식 (스프링이 찾지 못한다는 의미)
    
    .참고 - 애노테이션 없으면 발생하는 에러
    Parameter 0 of constructor in hello.hellospring.controller.MemberController required a bean of type 'hello.hellospring.service.MemberService' that could not be found.
    Consider defining a bean of type 'hello.hellospring.service.MemberService' in your configuration.
    --> 빈을 못찾겠으니 빈 등록 하라는 의미. 그리고 인터페이스니까 오류가 생긴다.
    --> 애노테이션을 붙이면 구현객체, 즉 MemoryMemberRepository를 자동으로 주입해준다
    
    .@Autowired 애노테이션 붙이는 위치
    생성자에 붙이도록 한다
     */

    //MemberRepository 객체 주입하기
    //new 연산자로 생성하지 않고 외부에서 주입되도록 설정
    //new 로 선언하지 않고 주입했기 때문에 동일한 인스턴스를 보장할 수 있음

    /*
    주입
        .MemberService 에 MemberRepository 객체를 주입해준다
     */

    private final MemberRepository memberRepository;
//    @Autowired
    public MemberService(MemberRepository memberRepository) { this.memberRepository = memberRepository; }

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복된 이름이 없는지 확인하기. 중복된 이름이 있으면 예외 발생
    private void validateDuplicateMember(Member member){
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent( m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        //좀더 짧게 작성한 코드
        memberRepository.findByName(member.getName())
                        .ifPresent( m -> {
                            throw new IllegalStateException("duplicate error");
                        });
    }

    public List<Member> findMembers(){ return memberRepository.findAll(); }

    Optional<Member> findOne(Long memberId){ return memberRepository.findById(memberId); }
}
