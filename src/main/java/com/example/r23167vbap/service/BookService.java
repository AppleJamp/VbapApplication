package com.example.r23167vbap.service;


import com.example.r23167vbap.model.dto.book.BookCreateDTO;
import com.example.r23167vbap.model.dto.book.BookGetDTO;
import com.example.r23167vbap.model.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    BookGetDTO create(BookCreateDTO bookCreateDTO);
    BookGetDTO update(UUID id, BookCreateDTO bookCreateDTO);
    BookGetDTO save(Book book);
    void delete(UUID id);
    List<BookGetDTO> getAll();
    BookGetDTO get(UUID id);
}
