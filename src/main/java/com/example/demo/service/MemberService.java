package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스는 비즈니스를 처리하는게 서비스 그 롤에 맞도록 명명함.
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository ;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository =memberRepository;
    }

    /*
    * 회원 가입
    */

    public Long join(Member member){
        validDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m ->{
        throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
