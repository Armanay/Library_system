package com.example.library.events.handlers;

import com.example.library.events.ReturnBookEvent;
import com.example.library.events.StudentBorrowBookEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ReturnBookHandler implements ApplicationListener<ReturnBookEvent> {

    @Override
    public void onApplicationEvent(ReturnBookEvent returnBookEvent) {
        System.out.println("Hi, this " + returnBookEvent.book().getTitle() + " is available now!!!!!!");
    }
}
