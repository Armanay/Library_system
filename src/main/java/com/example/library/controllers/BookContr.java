package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.jpaRepositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class BookContr {
    @Autowired
    private BookRepository repository;

    public List<Book> books(){
        return repository.booklist();
    }

    public void addBook(Book book){
        repository.save(book);
    }

    public Book findById(Long id){
        return repository.findById(id).get();
    }
}
