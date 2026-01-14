package com.project.book_catalog_system.service;

import com.project.book_catalog_system.payload.BookDTO;
import com.project.book_catalog_system.payload.BookResponse;

public interface BookService {

    BookDTO createBook(BookDTO bookDTO);

    BookResponse getAllBooks();

    BookDTO getBookById(Long id);

    BookDTO updateBook(BookDTO bookDTO, Long id);

    BookDTO deleteBook(Long id);

}
