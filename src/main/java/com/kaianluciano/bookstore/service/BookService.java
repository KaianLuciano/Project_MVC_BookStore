package com.kaianluciano.bookstore.service;

import com.kaianluciano.bookstore.model.BookModel;
import com.kaianluciano.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    final
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public boolean existsByTitulo(String bookTitle) {
        return bookRepository.existsByTitulo(bookTitle);
    }

    public List<BookModel> findAll(){
        return bookRepository.findAll();
    }

    public Optional<BookModel> findById(Long isbn){
        return bookRepository.findById(isbn);
    }

    @Transactional
    public void delete(BookModel bookModel) {
        bookRepository.delete(bookModel);
    }

}
