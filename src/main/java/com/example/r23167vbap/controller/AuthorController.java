package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.author.AuthorCreateDTO;
import com.example.r23167vbap.model.dto.author.AuthorGetDTO;
import com.example.r23167vbap.model.dto.author.AuthorCreateDTO;
import com.example.r23167vbap.model.dto.author.AuthorGetDTO;
import com.example.r23167vbap.service.AuthorService;
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
@RequestMapping("/author")
@Tag(name = "Author", description = "API for authors")
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Create author",
            description = "Create author with given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Author was successfully created",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorGetDTO createAutor(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        return authorService.create(authorCreateDTO);
    }

    @Operation(summary = "Update author",
            description = "Update author with given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Author was successfully updated",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Author was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorGetDTO updateAuthor(@Valid @PathVariable("id") UUID id, @Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        return authorService.update(id, authorCreateDTO);
    }

    @Operation(summary = "Delete author",
            description = "Delete author with given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Author was successfully deleted",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Author was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthor(@Valid @PathVariable("id") UUID id) {
        authorService.delete(id);
    }

    @Operation(summary = "Get author",
            description = "Get author with given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Author was successfully found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Author was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorGetDTO getAuthor(@Valid @PathVariable("id") UUID id) {
        return authorService.get(id);
    }

    @Operation(summary = "Get all authors",
            description = "Get all authors",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authors were successfully found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Authors were not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorGetDTO> getAllAuthor() {
        return authorService.getAll();
    }
}
