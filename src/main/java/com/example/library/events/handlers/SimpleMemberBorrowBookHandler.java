package com.example.library.events.handlers;

import com.example.library.events.StudentBorrowBookEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleMemberBorrowBookHandler implements ApplicationListener<StudentBorrowBookEvent> {

    @Override
    public void onApplicationEvent(StudentBorrowBookEvent studentBorrowBookEvent) {
        System.out.println(studentBorrowBookEvent.getBorrowedBook().getMember().getName() + " you have 1 month to return this book, because you are " + studentBorrowBookEvent.getBorrowedBook().getMember());
    }
}
