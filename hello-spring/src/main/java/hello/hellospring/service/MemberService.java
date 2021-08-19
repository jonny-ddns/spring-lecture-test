package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //MemberRepository 객체를 new 연산자로 생성하지 않고
    //외부에서 주입되도록 변경함
    //--> 동일한 인스턴스를 보장
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent( m-> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        //좀더 짧게 작성한 코드
        memberRepository.findByName(member.getName())
                .ifPresent( m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
