package com.project.book_catalog_system.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.book_catalog_system.payload.BookDTO;
import com.project.book_catalog_system.payload.BookResponse;
import com.project.book_catalog_system.service.BookService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBookDTO = bookService.createBook(bookDTO);
        
        return new ResponseEntity<>(createdBookDTO, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<BookResponse> getAllBooks() {
        BookResponse bookResponse = bookService.getAllBooks();

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        BookDTO updatedBookDTO = bookService.updateBook(bookDTO, id);
        
        return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        BookDTO deletedBookDTO = bookService.deleteBook(id);
        
        return new ResponseEntity<>(deletedBookDTO, HttpStatus.OK);
    }

}
