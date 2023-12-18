package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional //sql 이 작동하기 전 상황으로 돌려주는 어노테이션임 좋네
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService ;
    @Autowired MemberRepository memberRepository ;

    // 각각 테스트를 다시하도록
    // 스프링 테스트에서는 불필요
//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//    @AfterEach
//    public void afterEach(){
//        memberRepository.clearStore();
//    }
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("ronaldo");

        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember =memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void duplicateJoin(){
        //given
        Member member1 = new Member();
        member1.setName("11");

        Member member2 = new Member();
        member2.setName("11");
        //when
        memberService.join(member1);
        // 이 에러가 떠야 정상이야 중복일때~
        //then
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
//        try{
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage().equals("이미 존재하는 회원입니다,"));
//        }
    }

    @Test
    void findMembers() {
        //given

        //when

        //then
    }

    @Test
    void findOne() {
        //given

        //when

        //then
    }
}