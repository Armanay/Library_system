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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@Service
public class BorrowedBookContr implements ApplicationEventPublisherAware {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private MemberRepository memberRepository;

    public List<BorrowedBook> borrowedBooks(){

        return borrowedBookRepository.findAll();
    }
    public BorrowedBook getById(Long id)
    {
        return borrowedBookRepository.findById(id).get();
    }
    public void borrowBook(Book book, Member member){
        Date date = new Date();
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


        }

    }
    public void returnBook(Book book, Member member){

        for(BorrowedBook borrowedBook:borrowedBookRepository.findAll()){
            if(borrowedBook.getMember().getId() == member.getId() ) {
                if (borrowedBook.getBook().getId() == book.getId()) {
                    borrowedBookRepository.delete(borrowedBook);
                    System.out.println("Molodec kitap karyz emessin");
                }
                else {
                    System.out.println("Senin algan kitaptarynnyn ishinde ondai kitap zhok");
                }
            }
            else {
                System.out.println("Ondai member zhok");
            }
        }


    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    public void decreaseBookQuantity(Book book){
        book.setQuantity(book.getQuantity()-1);
    }
}
