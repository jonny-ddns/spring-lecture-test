package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

//Bean, DB연결 등을 설정하는 클래스

//자바코드로 스프링 빈 등록하기
//자바코드로 연결하면 설정하는 작업은 애노테이션보다 간단하지 않지만 대신 유지보수는 간단하다

//@Configuration 애노테이션
@Configuration
public class SpringConfig {

    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //스프링 객체로 활용되도록 bean 으로 등록
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    //DB연결 - memory, Jdbc, JPA
    //DI원칙 - 기존코드는 건드리지 않고, 설정만으로 구현클래스 변경하기
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);

    }
}
