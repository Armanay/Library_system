package com.example.library.events;

import com.example.library.entities.BorrowedBook;
import org.springframework.context.ApplicationEvent;

public class SimpleMemberBorrowBookEvent extends ApplicationEvent {
    private BorrowedBook borrowedBook;

    public SimpleMemberBorrowBookEvent(Object source, BorrowedBook borrowedBook) {
        super(source);
        this.borrowedBook = borrowedBook;
    }
    public BorrowedBook getBorrowedBook(){
        return this.borrowedBook;
    }
}
