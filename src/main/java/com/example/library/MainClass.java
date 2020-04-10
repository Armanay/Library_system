package com.example.library;

import com.example.library.controllers.*;
import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.entities.Member;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    static Scanner cin = new Scanner(System.in);
    static BufferedReader cinString = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);
        MemberContr memberContr = context.getBean("memberContr", MemberContr.class);
        BookContr bookContr = context.getBean("bookContr", BookContr.class);
        BorrowedBookContr borrowedBookContr = context.getBean("borrowedBookContr", BorrowedBookContr.class);
        GenreContr genreContr = context.getBean("genreContr", GenreContr.class);
        ReturnedBookContr returnedBookContr = context.getBean("returnedBookContr", ReturnedBookContr.class);
        try {
            String poka = "";
            while (poka.equals("")) {
                System.out.println("1.Members");
                System.out.println("2.Books");
                System.out.println("3. Genres");
                System.out.println("4. Borrow Book");
                System.out.println("5. Return Book");
                System.out.println("6. New Member");
                System.out.println("7. New Book");
                System.out.println("8. New Genre");
                System.out.println("0. Poka");
                int operation = cin.nextInt();
                switch (operation) {
                    case 1:
                        System.out.println(memberContr.members());
                        break;
                    case 2:
                        System.out.println(bookContr.books());
                        break;
                    case 3:
                        System.out.println(genreContr.genres());
                        break;
                    case 4:
                        System.out.println(memberContr.members());
                        System.out.print("Member id = ");
                        Long memberid = cin.nextLong();
                        System.out.println(bookContr.books());
                        System.out.print("Book id =");
                        Long bookid = cin.nextLong();
                        borrowedBookContr.borrowBook(bookContr.findById(bookid), memberContr.findById(memberid));

                        break;
                    case 5:
                        System.out.println(memberContr.members());
                        System.out.print("Member id = ");
                        Long returnmemberid = cin.nextLong();
                        System.out.println(memberContr.findById(returnmemberid).getBorrowedBooks());
                        System.out.print("Book id =");
                        Long returnbookid = cin.nextLong();
                        borrowedBookContr.returnBook(bookContr.findById(returnbookid), memberContr.findById(returnmemberid));
                        returnedBookContr.returnBook(bookContr.findById(returnbookid), memberContr.findById(returnmemberid));
                        break;
                    case 6:

                        Member member = new Member();
                        System.out.print("name: ");
                        String name = cin.next();
                        System.out.println("");
                        System.out.print("surname: ");
                        String surname = cin.next();
                        System.out.println("");
                        System.out.print("phone: ");
                        String phone = cin.next();
                        System.out.println("");
                        System.out.print("email: ");
                        String email = cin.next();
                        System.out.println("");
                        System.out.println("1. Simmple member");
                        System.out.println("2. Student");
                        int memberType = cin.nextInt();
                        switch (memberType) {
                            case 1:
                                member.setMemberType(MemberType.SimpleMember);
                                break;
                            case 2:
                                member.setMemberType(MemberType.Student);
                                break;
                        }
                        member.setName(name);
                        member.setSurname(surname);
                        member.setPhone(phone);
                        member.setEmail(email);
                        memberContr.addMember(member);
                        break;
                    case 7:
                        Book book = new Book();
                        System.out.print("title : ");
                        String title = cinString.readLine();
                        System.out.println("");
                        System.out.print("author : ");
                        String author = cinString.readLine();
                        System.out.println("");
                        System.out.print("quantity : ");
                        int quantity = cin.nextInt();
                        book.setAuthor(author);
                        book.setQuantity(quantity);
                        book.setTitle(title);
                        book.setAvailable(true);
                        List<Genre> genres = new ArrayList<>();
                        System.out.println("\n" + genreContr.genres());
                        System.out.print("choose genre : ");
                        Long genreid = cin.nextLong();
                        genres.add(genreContr.findById(genreid));
                        String pokapoka = "";
                        while (pokapoka.equals("")){
                            System.out.print("One more genre? y/n");
                            String yOrn = cinString.readLine();
                            switch (yOrn){
                                case "y":
                                    System.out.println("\n" + genreContr.genres());
                                    System.out.print("choose genre : ");
                                    Long newgenreid = cin.nextLong();
                                    genres.add(genreContr.findById(newgenreid));
                                    break;
                                case "n":
                                    pokapoka = "pokapoka";
                                    break;
                            }
                        }
                        book.setGenreList(genres);
                        bookContr.addBook(book);
                        break;
                    case 8:
                        Genre genre = new Genre();
                        System.out.print("\n" + "genre name : ");
                        String gname = cin.next();
                        genre.setName(gname);
                        genreContr.addGenre(genre);
                        break;
                    case 0:
                        poka = "poka";
                        break;
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getLocalizedMessage());

        }
    }

}
