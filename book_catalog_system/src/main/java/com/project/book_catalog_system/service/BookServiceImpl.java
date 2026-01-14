package com.project.book_catalog_system.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.book_catalog_system.exceptions.APIException;
import com.project.book_catalog_system.exceptions.ResourceNotFoundException;
import com.project.book_catalog_system.model.Book;
import com.project.book_catalog_system.payload.BookDTO;
import com.project.book_catalog_system.payload.BookResponse;
import com.project.book_catalog_system.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        
        Book savedBook = bookRepository.save(book);

        BookDTO savedBookDTO = modelMapper.map(savedBook, BookDTO.class);

        return savedBookDTO;
    }

    @Override
    public BookResponse getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if(books.isEmpty()) {
            throw new APIException("No books added");
        }

        List<BookDTO> bookDTOs = books.stream()
            .map(book -> modelMapper.map(book, BookDTO.class))
            .toList();

        BookResponse bookResponse = new BookResponse(bookDTOs);

        return bookResponse;
    }
        

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

        return bookDTO;
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long id) {
        Book book = modelMapper.map(bookDTO, Book.class);
        
        Book bookFromDB = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setId(bookFromDB.getId());
        Book updatedBook = bookRepository.save(book);

        BookDTO updatedBookDTO = modelMapper.map(updatedBook, BookDTO.class);

        return updatedBookDTO;
    }

    @Override
    public BookDTO deleteBook(Long id) {
        Book bookFromDB = bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        bookRepository.deleteById(id);

        BookDTO deleteBookDTO = modelMapper.map(bookFromDB, BookDTO.class);

        return deleteBookDTO;
    }

}
