package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.jpaRepositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookContr {
    @Autowired
    private BookRepository repository;

    @GetMapping("")
    public List<Book> books(){
        return repository.booklist();
    }
    @PostMapping("")
    public void addBook(@RequestBody Book book){
        repository.save(book);
    }
    @GetMapping("/{id}")
    public Book findById(@PathVariable("id") Long id){
        return repository.findById(id).get();
    }

    @GetMapping("/find/")
    public List<Book> getByAuthor(@RequestParam String author){
        return repository.findBookByAuthor(author);
    }

    @PatchMapping("/{id}")
    public Book updateBookQuantity(@PathVariable Long id, @RequestParam int quantity){
        Book book = repository.findById(id).get();
        book.setQuantity(quantity);
        return repository.save(book);
    }
}
