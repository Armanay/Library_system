package com.example.library.server.implementation;

import com.example.library.entities.Member;
import com.example.library.jpaRepositories.MemberRepository;
import com.example.library.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(s);
        if (member == null){
            throw new UsernameNotFoundException("Member not found!ы");
        }
        return member;
    }

    @Override
    public List<Member> members() {
        return memberRepository.findAll();
    }

    @Override
    public void addMember(Member member) {
       member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    @Override
    public void removeMember(Long id) {
        Member member = memberRepository.findById(id).get();
        memberRepository.delete(member);
    }

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).get();
    }
}
