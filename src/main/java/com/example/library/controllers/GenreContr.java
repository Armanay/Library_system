package com.example.library.controllers;

import com.example.library.entities.Book;
import com.example.library.entities.Genre;
import com.example.library.jpaRepositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreContr {
    @Autowired
    private GenreRepository repository;

    @GetMapping("")
    public List<Genre> genres(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Genre findById(@PathVariable("id") Long id){
        return repository.findById(id).get();
    }

    @PostMapping("")
    public void addGenre(@RequestBody Genre genre){
        repository.save(genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable("id") Long id, @RequestBody Genre genre)
    {
        genre.setId(id);
        return repository.save(genre);
    }
}
