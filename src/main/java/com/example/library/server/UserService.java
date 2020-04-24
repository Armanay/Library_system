package com.example.library.server;

import com.example.library.entities.Member;

import java.util.List;

public interface UserService {
    List<Member> members();
    void addMember(Member member);
    void removeMember(Long id);
    Member findMemberById(Long id);
}
