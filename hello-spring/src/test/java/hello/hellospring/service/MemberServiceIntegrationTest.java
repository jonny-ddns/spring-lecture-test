package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//통합테스트
//스프링 컨테이너와 DB연결

//@SpringBootTest; 스프링 컨테이너와 테스트 함께실행. 주석처리하면 자바코드만으로 테스트를 진행함
//transactional; 트랜잭션. DB에 데이터를 적용하지 않고 rollback 함
@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

//    @Autowired MemberRepository memberRepository = new MemoryMemberRepository();
//    @Autowired MemberService memberService = new MemberService(memberRepository);

//    생성자에 @애노테이션을 지정해 자동으로 의존성 주입
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void save() {
        Member member = new Member();
        member.setName("helloWorld");

        Long memberId = memberService.join(member);
        Member findMember = memberRepository.findById(memberId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //중복
    @Test
//    @Commit
    public void duplicateNameTest() {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));      //예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("duplicate error");
    }

    @Test
    public void findAll(){
    }

    @Test
    public void findById(){
    }

    @Test
    public void findByName(){
    }

    @Test
    public void test(){
    }
}