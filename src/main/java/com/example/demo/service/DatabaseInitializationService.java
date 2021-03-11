package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitializationService implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public void run(String... args) throws Exception {

        if (bookRepository.findAll().size() != 0) {
            return;
        }



        bookRepository.save(new Post(null, "Sebaste", "Sebaste", "Sebaste", "Sebaste"));
        bookRepository.save(new Post(null, "Sebaste", "Sebaste", "Sebaste", "Sebaste"));


    }
}
