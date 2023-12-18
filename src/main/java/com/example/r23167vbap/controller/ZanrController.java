package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.dto.zanr.in.ZanrCreateDTO;
import com.example.r23167vbap.model.dto.zanr.out.ZanrGetDTO;
import com.example.r23167vbap.service.ZanrService;
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
@RequestMapping("/zanr")
@Tag(name = "Zanr", description = "API pro žánr")
public class ZanrController {
    private final ZanrService zanrService;

    @Operation(summary = "Vytvoření žánru",
            description = "Vytvoření žánru podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Žánr byl úspěšně vytvořen",
                            useReturnTypeSchema = true
                    ),
            })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ZanrGetDTO createZanr(@Valid @RequestBody ZanrCreateDTO zanrCreateDTO) {
        return zanrService.create(zanrCreateDTO);
    }

    @Operation(summary = "Aktualizace žánru",
            description = "Aktualizace žánru podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Žánr byl úspěšně aktualizován",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Žánr nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZanrGetDTO updateZanr(@Valid @PathVariable("id") UUID id, @Valid @RequestBody ZanrCreateDTO zanrCreateDTO) {
        return zanrService.update(id, zanrCreateDTO);
    }

    @Operation(summary = "Smazání žánru",
            description = "Smazání žánru podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Žánr byl úspěšně smazán",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Žánr nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteZanr(@Valid @PathVariable("id") UUID id) {
        zanrService.delete(id);
    }

    @Operation(summary = "Získání žánru",
            description = "Získání žánru podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Žánr byl úspěšně získán",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Žánr nebyl nalezen",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ZanrGetDTO getZanr(@Valid @PathVariable("id") UUID id) {
        return zanrService.get(id);
    }

    @Operation(summary = "Získání všech žánrů",
            description = "Získání všech žánrů podle zadaných parametrů",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Žánry byly úspěšně získány",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Žánry nebyly nalezeny",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ZanrGetDTO> getAllZanr() {
        return zanrService.getAll();
    }
}
