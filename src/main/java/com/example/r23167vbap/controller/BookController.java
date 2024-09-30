package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.book.BookCreateDTO;
import com.example.r23167vbap.model.dto.book.BookGetDTO;
import com.example.r23167vbap.model.dto.book.BookCreateDTO;
import com.example.r23167vbap.model.dto.book.BookGetDTO;
import com.example.r23167vbap.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Tag(name = "Book", description = "API for Book")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Creation of a book",
            description = "Creation of a book with the given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Book was successfully created",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookGetDTO createBook(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.create(bookCreateDTO);
    }

    @Operation(summary = "Update of a book",
            description = "Update of a book with the given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book was successfully updated",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookGetDTO updateBook(@Valid @PathVariable("id") UUID id, @Valid @RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.update(id, bookCreateDTO);
    }

    @Operation(summary = "Deletion of a book",
            description = "Deletion of a book with the given id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Book was successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@Valid @PathVariable("id") UUID id) {
        bookService.delete(id);
    }

    @Operation(summary = "Get a book",
            description = "Get a book with the given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Book was successfully found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookGetDTO getBook(@Valid @PathVariable("id") UUID id) {
        return bookService.get(id);
    }

    @Operation(summary = "Get all books",
            description = "Get all books",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Books were successfully found",
                            useReturnTypeSchema = true
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookGetDTO> getAllBook() {
        return bookService.getAll();
    }
}
