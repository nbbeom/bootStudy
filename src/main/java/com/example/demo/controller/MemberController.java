package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 아래와 같이 여러번 객체를 만들 필요가 없는 경우에
    // 의존성 주입을 사용한다.
    // 스프링 빈은
    // 컴포넌트 스캔방식
    // 자바 코드로 직접 등록 하는 방식이 있는데
    // 이 코드는 @Controller . @Service . @Repository 로 설정해 컴포넌트 스캔 했음.
    // DemoApplication package 하위만 따라 들어가서 스캔함.
//    private final MemberService memberService= new MemberService();

    private final MemberService memberService;

    @Autowired // dependency injection
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
