package com.example.library.events;

import com.example.library.entities.Book;
import com.example.library.entities.BorrowedBook;
import org.springframework.context.ApplicationEvent;

public class ReturnBookEvent extends ApplicationEvent {
    private Book book;

    public ReturnBookEvent(Object source, Book book) {
        super(source);
        this.book = book;
    }
    public Book book(){
        return this.book;
    }
}
