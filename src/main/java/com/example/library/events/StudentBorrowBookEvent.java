package com.example.library.events;

import com.example.library.entities.BorrowedBook;
import org.springframework.context.ApplicationEvent;

public class StudentBorrowBookEvent extends ApplicationEvent {
    private BorrowedBook borrowedBook;

    public StudentBorrowBookEvent(Object source, BorrowedBook borrowedBook) {
        super(source);
        this.borrowedBook = borrowedBook;
    }
    public BorrowedBook getBorrowedBook(){
        return this.borrowedBook;
    }
}
