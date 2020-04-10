package com.example.library.jpaRepositories;

import com.example.library.entities.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {
}
