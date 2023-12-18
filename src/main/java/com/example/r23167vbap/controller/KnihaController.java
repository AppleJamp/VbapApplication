package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.kniha.in.KnihaCreateDTO;
import com.example.r23167vbap.model.dto.kniha.out.KnihaGetDTO;
import com.example.r23167vbap.service.KnihaService;
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
@RequestMapping("/kniha")
@Tag(name = "Kniha", description = "API pro knihu")
public class KnihaController {
    private final KnihaService knihaService;

    @Operation(summary = "Vytvoření knihy",
            description = "Vytvoření knihy podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Kniha byla úspěšně vytvořena",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KnihaGetDTO createKniha(@Valid @RequestBody KnihaCreateDTO knihaCreateDTO) {
        return knihaService.create(knihaCreateDTO);
    }

    @Operation(summary = "Aktualizace knihy",
            description = "Aktualizace knihy podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kniha byla úspěšně aktualizována",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Kniha nebyla nalezena",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KnihaGetDTO updateKniha(@Valid @PathVariable("id") UUID id, @Valid @RequestBody KnihaCreateDTO knihaCreateDTO) {
        return knihaService.update(id, knihaCreateDTO);
    }

    @Operation(summary = "Smazání knihy",
            description = "Smazání knihy podle zadaného id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Kniha byla úspěšně smazána"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Kniha nebyla nalezena",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKniha(@Valid @PathVariable("id") UUID id) {
        knihaService.delete(id);
    }

    @Operation(summary = "Získání knihy",
            description = "Získání knihy podle zadaného id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Kniha byla úspěšně získána",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Kniha nebyla nalezena",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public KnihaGetDTO getKniha(@Valid @PathVariable("id") UUID id) {
        return knihaService.get(id);
    }

    @Operation(summary = "Získání všech knih",
            description = "Získání všech knih",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Knihy byly úspěšně získány",
                            useReturnTypeSchema = true
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<KnihaGetDTO> getAllKniha() {
        return knihaService.getAll();
    }
}
