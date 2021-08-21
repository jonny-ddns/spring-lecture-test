package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
test하는 팁
.given > when > then 순서로 진행하기
.무엇을
.어떤 작업을 했을때
.어떤 결과가 나와야 한다
 */

class MemberServiceTest {
    MemberService memberService;
    MemberRepository memberRepository;

    //매번 테스트 전 수행 - 인스턴스 새로 생성하기
    @BeforeEach
    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
        memberService = new MemberService(new MemoryMemberRepository());
    }

    //매번 테스트 후 수행
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {    }

    @Test
    void findOne() {    }

    //이름 중복값을 넣었을 때 정상적으로 예외가 발생하는지 확인
    @Test
    public void makeDuplicateMember(){
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        /* 테스트방법1 */
        //when
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();     //이 구문 실행되면 실패
//        } catch (IllegalStateException ise){
//            Assertions.assertThat(ise.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }

        /* 테스트방법2 */
        memberService.join(member1);
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        IllegalStateException ise = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //오류 메시지 검증하기
        Assertions.assertThat(ise.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}