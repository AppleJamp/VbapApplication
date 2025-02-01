package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.exception.NotFoundException;
import com.example.r23167vbap.model.dto.book.BookCreateDTO;
import com.example.r23167vbap.model.dto.book.BookGetDTO;
import com.example.r23167vbap.model.entity.Book;
import com.example.r23167vbap.repository.BookRepository;
import com.example.r23167vbap.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookGetDTO create(BookCreateDTO bookCreateDTO) {
        log.info("create({})", bookCreateDTO);
        return this.save(modelMapper.map(bookCreateDTO, Book.class));
    }

    @Override
    public BookGetDTO update(UUID id, BookCreateDTO bookCreateDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id: " + id + " not found"));
        modelMapper.map(bookCreateDTO, book);

        log.info("update({})", bookCreateDTO);
        return this.save(book);
    }

    @Override
    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookGetDTO get(UUID id) {
        return modelMapper.map(bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id: " + id + " not found")), BookGetDTO.class);
    }

    @Override
    public List<BookGetDTO> getAll() {
        return bookRepository.findAll().stream().map(book -> modelMapper.map(book, BookGetDTO.class)).toList();
    }

    @Override
    public BookGetDTO save(Book book) {
        return modelMapper.map(bookRepository.save(book), BookGetDTO.class);
    }
}
