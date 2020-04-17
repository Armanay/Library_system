package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.entities.BorrowedBook;
import com.example.library.entities.Member;
import com.example.library.entities.ReturnedBook;
import com.example.library.events.ReturnBookEvent;
import com.example.library.events.SimpleMemberBorrowBookEvent;
import com.example.library.jpaRepositories.BookRepository;
import com.example.library.jpaRepositories.BorrowedBookRepository;
import com.example.library.jpaRepositories.MemberRepository;
import com.example.library.jpaRepositories.ReturnedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/returnedBooks")
public class ReturnedBookContr implements ApplicationEventPublisherAware {
    @Autowired
    private ReturnedRepository returnedRepository;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;


    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("")
    public List<ReturnedBook> returnedBooks(){
        return returnedRepository.findAll();
    }

    @PostMapping("/{memberid}/{bookid}")
    public ReturnedBook returnBook(@PathVariable("memberid") Long memberid, @PathVariable("bookid") Long bookid){
        Member member = memberRepository.findById(memberid).get();
        Book book = bookRepository.findById(bookid).get();
        Date date = new Date();
        ReturnedBook returnedBook = new ReturnedBook();
        returnedBook.setBook(book);
        returnedBook.setMember(member);
        returnedBook.setReturnDate(date);
        this.returnBook(member, book);
        this.increaseBookQuantity(book);
        memberRepository.save(member);
        bookRepository.save(book);
        returnedRepository.save(returnedBook);
        this.eventPublisher.publishEvent(new ReturnBookEvent(this,book));
        return  returnedBook;
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher=applicationEventPublisher;
    }

    public void increaseBookQuantity(Book book){
        book.setQuantity(book.getQuantity()+1);
    }
    public void returnBook(Member member,Book book){
        member.getBorrowedBooks().remove(book);
        member.setBorrowedBooks(member.getBorrowedBooks());
    }
}
