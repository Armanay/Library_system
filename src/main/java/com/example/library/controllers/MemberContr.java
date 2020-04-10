package com.example.library.controllers;

import com.example.library.entities.Member;
import com.example.library.jpaRepositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MemberContr {
    @Autowired
    private MemberRepository repository;

    public List<Member> members(){
        return repository.findAll();
    }

    public Member findById(Long id)
    {
        return repository.findById(id).get();
    }

    public void addMember(Member member){
        repository.save(member);
    }

    public void removeClient(Member member){
        repository.delete(member);
    }
}
