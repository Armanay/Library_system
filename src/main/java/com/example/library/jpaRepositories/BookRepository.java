package com.example.library.jpaRepositories;

import com.example.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.isAvailable = true  AND b.quantity > 0")
    List<Book> booklist();

    List<Book> findBookByAuthor(String author);
}
