package com.kaianluciano.bookstore.controller;

import com.kaianluciano.bookstore.model.BookModel;
import com.kaianluciano.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BookController {

    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody BookModel bookModel) {
        //Will test if there is already a book with the same title
        if(bookService.existsByTitulo(bookModel.getTitulo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book title entered already exists");
        }

        //Will return a ResponseBody if this operation it was a success
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookModel));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        //Will return all existing books
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") Long isbn){
        //Will testing if existing one book with the id received
        //If it does not exist, it will return to a body alerting
        Optional<BookModel> bookModelOptional = bookService.findById(isbn);
        if(!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        //Will return a ResponseBody if this operation it was a success
        return ResponseEntity.status(HttpStatus.OK).body(bookModelOptional.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long isbn){
        //Will testing if existing one book with the id received
        //If it does not exist, it will return to a body alerting
        Optional<BookModel> bookModelOptional = bookService.findById(isbn);
        if(!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        //Deletes the object specified by the id provided by the request
        bookService.delete(bookModelOptional.get());

        //Will return a ResponseBody if this operation it was a success
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody BookModel bookModel){
        //Will testing if existing one book with the id received
        //If it does not exist, it will return to a body alerting
        Optional<BookModel> bookModelOptional = bookService.findById(id);
        if(!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }

        //Creates an auxiliary object that will have its attributes set with the attributes of the received object
        var bookModelPut = bookModelOptional.get();
        bookModelPut.setAutor(bookModel.getAutor());
        bookModelPut.setGenero(bookModel.getGenero());
        bookModelPut.setPreco(bookModel.getPreco());
        bookModelPut.setTitulo(bookModel.getTitulo());

        //Will return a ResponseBody if this operation it was a success
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookModelPut));
    }

}
