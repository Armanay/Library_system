package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.jpaRepositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class GenreContr {
    @Autowired
    private GenreRepository repository;

    public List<Genre> genres(){
        return repository.findAll();
    }

    public Genre findById(Long id){
        return repository.findById(id).get();
    }

    public void addGenre(Genre genre){
        repository.save(genre);
    }
}
