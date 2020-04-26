package com.example.library.controllers;

import com.example.library.MemberType;
import com.example.library.entities.Book;
import com.example.library.entities.BorrowedBook;
import com.example.library.entities.Member;
import com.example.library.events.SimpleMemberBorrowBookEvent;
import com.example.library.events.StudentBorrowBookEvent;
import com.example.library.jpaRepositories.BookRepository;
import com.example.library.jpaRepositories.BorrowedBookRepository;
import com.example.library.jpaRepositories.MemberRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrowedBooks")
public class BorrowedBookContr implements ApplicationEventPublisherAware {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private MemberRepository memberRepository;

    @ApiOperation(value = "Borrow book")
    @PostMapping("/{memberid}/{bookid}")
    public BorrowedBook borrowBook( @PathVariable("memberid") Long memberid, @PathVariable("bookid") Long bookid){
        Date date = new Date();
        Member member = memberRepository.findById(memberid).get();
        Book book = bookRepository.findById(bookid).get();
        if(book.getQuantity() == 0 ){
            System.out.println("Quantity = 0");
        }
        else {
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setBook(book);
            borrowedBook.setMember(member);
            borrowedBook.setBorrowDate(date);

            this.decreaseBookQuantity(book);
            bookRepository.save(book);
            borrowedBookRepository.save(borrowedBook);
            if (member.getMemberType() == MemberType.Student){
                this.eventPublisher.publishEvent(new StudentBorrowBookEvent(this,borrowedBook));
            }
            return borrowedBook;

        }
        return null;
    }

    @ApiOperation(value = "Return Borrowed book")
    @DeleteMapping("/{memberid}/{bookid}")
    public BorrowedBook returnBook(@PathVariable("memberid") Long memberid, @PathVariable("bookid") Long bookid){

        for(Member member1:memberRepository.findAll()){
            if (member1.getId().equals(memberid)) {
                for (BorrowedBook borrowedBook : member1.getBorrowedBooks()) {
                    if (borrowedBook.getBook().getId().equals(bookid)) {
                            borrowedBookRepository.delete(borrowedBook);
                            return borrowedBook;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    public void decreaseBookQuantity(Book book){
        book.setQuantity(book.getQuantity()-1);
    }
}
