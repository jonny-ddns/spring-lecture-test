package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import java.util.*;

//@Repository 애노테이션
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 8L;      //일련번호

    @Override
    public Member save(Member member) {
        member.setId(++sequence);       //일련번호 증가
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //람다를 활용해 1)스트림 2)필터링 3)원하는 항목 찾기
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
    }

    //리스트 반환
    //.values() --> Collection 인터페이스 객체 반환
    //ArrayList 의 매개값으로 전달해 List 로 가져올 수 있음
    @Override
    public List<Member> findAll() { return new ArrayList<>(store.values()); }

    @Override
    public void clearStore() {
        store.clear();
    }
}
