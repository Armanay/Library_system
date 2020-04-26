package com.example.library.controllers;

import com.example.library.entities.Member;
import com.example.library.server.UserService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "All Memebers")
    @GetMapping("")
    public List<Member> members(){
        return userService.members();
    }

    @ApiOperation(value = "Find member by id")
    @GetMapping("/{id}")
    public Member findById(@PathVariable("id") Long id)
    {
        return userService.findMemberById(id);
    }

    @ApiOperation(value = "Add Member")
    @PostMapping()
    public void addMember(@RequestBody Member member){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());
        userService.addMember(member);
    }

    @ApiOperation(value = "Delete member by id")
    @DeleteMapping("/{id}")
    public void removeMember(@PathVariable("id") Long id)
    {
        userService.removeMember(id);
    }
}
