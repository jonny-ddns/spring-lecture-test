package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class MemoryMemberRepositoryTest {
//    MemberRepository repository = new MemoryMemberRepository();
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    
    //각 테스트 실시후에 메모리를 지워주도록 설정
    @AfterEach
    public void afterEach(){
        clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result : "+ (result == member));

        //assertEquals(예상값, 실제값)
//        Assertions.assertEquals(member, result);
//        Assertions.assertEquals(member, null);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Member result = repository.findByName("spring1").get();
        Member result = repository.findByName("spring2").get();
        Assertions.assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void clearStore(){
        repository.clearStore();
    }
}
