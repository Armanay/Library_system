package com.example.library.controllers;

import com.example.library.entities.Member;
import com.example.library.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberContr {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<Member> members(){
        return userService.members();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable("id") Long id)
    {
        return userService.findMemberById(id);
    }

    @PostMapping()
    public void addMember(@RequestBody Member member){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());
        userService.addMember(member);
    }

    @DeleteMapping("/{id}")
    public void removeMember(@PathVariable("id") Long id)
    {
        userService.removeMember(id);
    }
}
