package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.genre.GenreCreateDTO;
import com.example.r23167vbap.model.dto.genre.GenreGetDTO;
import com.example.r23167vbap.service.GenreService;
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
@RequestMapping("/genre")
@Tag(name = "Genre", description = "API for Genre")
public class GenreController {
    private final GenreService genreService;

    @Operation(summary = "Genre creation",
            description = "Create a new genre",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Genre was successfully created",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreGetDTO createGenre(@Valid @RequestBody GenreCreateDTO genreCreateDTO) {
        return genreService.create(genreCreateDTO);
    }

    @Operation(summary = "Update genre",
            description = "Update genre by given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Genre was successfully updated",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreGetDTO updateGenre(@Valid @PathVariable("id") UUID id, @Valid @RequestBody GenreCreateDTO genreCreateDTO) {
        return genreService.update(id, genreCreateDTO);
    }

    @Operation(summary = "Delete genre",
            description = "Delete genre by given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Genre was successfully deleted",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGenre(@Valid @PathVariable("id") UUID id) {
        genreService.delete(id);
    }

    @Operation(summary = "Get genre",
            description = "Get genre by given parameters",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Genre was successfully found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genre was not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreGetDTO getGenre(@Valid @PathVariable("id") UUID id) {
        return genreService.get(id);
    }

    @Operation(summary = "Get all genres",
            description = "Get all genres",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Genres were successfully found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Genres were not found",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreGetDTO> getAllGenre() {
        return genreService.getAll();
    }
}
