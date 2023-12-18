package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.autor.in.AutorCreateDTO;
import com.example.r23167vbap.model.dto.autor.out.AutorGetDTO;
import com.example.r23167vbap.service.AutorService;
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
@RequestMapping("/autor")
@Tag(name = "Autor", description = "API pro autora")
public class AutorController {
    private final AutorService autorService;

    @Operation(summary = "Vytvoření autora",
            description = "Vytvoření autora podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Autor byl úspěšně vytvořen",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorGetDTO createAutor(@Valid @RequestBody AutorCreateDTO autorCreateDTO) {
        return autorService.create(autorCreateDTO);
    }

    @Operation(summary = "Aktualizace autora",
            description = "Aktualizace autora podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autor byl úspěšně aktualizován",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutorGetDTO updateAutor(@Valid @PathVariable("id") UUID id, @Valid @RequestBody AutorCreateDTO autorCreateDTO) {
        return autorService.update(id, autorCreateDTO);
    }

    @Operation(summary = "Smazání autora",
            description = "Smazání autora podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autor byl úspěšně smazán",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAutor(@Valid @PathVariable("id") UUID id) {
        autorService.delete(id);
    }

    @Operation(summary = "Získání autora",
            description = "Získání autora podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autor byl úspěšně získán",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autor nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AutorGetDTO getAutor(@Valid @PathVariable("id") UUID id) {
        return autorService.get(id);
    }

    @Operation(summary = "Získání všech autorů",
            description = "Získání všech autorů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autoři byli úspěšně získáni",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Autoři nebyli nalezeni",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AutorGetDTO> getAllAutor() {
        return autorService.getAll();
    }
}
