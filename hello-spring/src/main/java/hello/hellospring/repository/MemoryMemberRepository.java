package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
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

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     //컬렉션 인터페이스 반환
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
