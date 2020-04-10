package com.example.library.jpaRepositories;

import com.example.library.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface MemberRepository extends JpaRepository<Member,Long> {
}
