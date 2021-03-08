package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Long add(BookDto bookDto) {
        Book addedBook = Book.builder()
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .criticalDescription(bookDto.getCriticalDescription())
                .build();
        bookRepository.save(addedBook);
        return addedBook.getId();
    }

    public List<BookDto> list() {
        List<BookDto> bookDtoList = new ArrayList<>();
        Iterable<Book> books = bookRepository.findAll();
        for (Book bookFind : books) {
            bookDtoList.add(
                    BookDto.builder()
                            .author(bookFind.getAuthor())
                            .title(bookFind.getTitle())
                            .description(bookFind.getDescription())
                            .criticalDescription(bookFind.getCriticalDescription())
                            .build());
        }
        return bookDtoList;
    }


    public void update(BookDto bookDto) {
        Optional<Book> editedBook = bookRepository.findById(bookDto.getId());
        if (editedBook.isPresent()) {
            Book book = editedBook.get();
            book.setAuthor(bookDto.getAuthor());
            book.setTitle(bookDto.getTitle());
            book.setDescription(bookDto.getDescription());
            book.setCriticalDescription(bookDto.getCriticalDescription());
            bookRepository.save(book);
        }
    }

    public void delete(Long userId) {
        bookRepository.deleteById(userId);
    }
}
