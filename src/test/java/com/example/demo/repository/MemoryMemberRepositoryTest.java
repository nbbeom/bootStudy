package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository() ;

    //콜백 메서드 테스트 한번당 저장소를 깔끔하게 지우려고 하는거임
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("테스트 코드 작성 memoryMemberRepository 를 테스트합니다");
        repository.save(member);

        Member res = repository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(res);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member res = repository.findByName("spring1").get();
        Assertions.assertThat(res).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> res = repository.findAll();
        Assertions.assertThat(res.size()).isEqualTo(2);

    }

}
