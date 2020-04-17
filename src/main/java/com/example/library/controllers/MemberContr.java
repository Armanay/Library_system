package com.example.library.controllers;

import com.example.library.entities.Member;
import com.example.library.jpaRepositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberContr {
    @Autowired
    private MemberRepository repository;

    @GetMapping("")
    public List<Member> members(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable("id") Long id)
    {
        return repository.findById(id).get();
    }

    @PostMapping("")
    public void addMember(@RequestBody Member member){
        repository.save(member);
    }

    @DeleteMapping("/{id}")
    public void removeClient(@PathVariable("id") Long id)
    {
        Member member = repository.findById(id).get();
        repository.delete(member);
    }
}
