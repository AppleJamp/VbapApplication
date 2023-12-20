package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.AuthenticationRequest;
import com.example.r23167vbap.model.AuthenticationResponse;
import com.example.r23167vbap.model.dto.uzivatel.in.UzivatelPostDTO;
import com.example.r23167vbap.model.dto.uzivatel.out.UzivatelGetDTO;
import com.example.r23167vbap.model.entity.Uzivatel;
import com.example.r23167vbap.service.UzivatelSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/uzivatel")
@Tag(name = "Uživatel", description = "API pro uživatele")
public class UzivatelController {
    private final UzivatelSerivce uzivatelSerivce;
    private final ModelMapper modelMapper;

    @Operation(
            description = "EP for registration",
            responses = {
                    @ApiResponse(
                            description = "Successful request, account was registered",
                            content = @Content(mediaType = "application/json"),
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Account already exists",
                            content = @Content(mediaType = "application/json"),
                            responseCode = "409"
                    ),
                    @ApiResponse(
                            description = "Validation error",
                            content = @Content(mediaType = "application/json"),
                            responseCode = "422"
                    )
            }
    )
    @PostMapping("/registration")
    public UzivatelGetDTO registerAccount(@Valid @RequestBody UzivatelPostDTO uzivatelPostDTO) {
        return modelMapper.map(
                uzivatelSerivce.createAccount(
                        modelMapper.map(uzivatelPostDTO, Uzivatel.class)
                ), UzivatelGetDTO.class
        );
    }

    @Operation(summary = "Authenticate account and obtain access token", responses = {
            @ApiResponse(
                    description = "Authentication successful",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    description = "Unauthorized",
                    responseCode = "401",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    description = "Account not found",
                    responseCode = "404",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    description = "Validation error",
                    content = @Content(mediaType = "application/json"),
                    responseCode = "422"
            )
    })
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        String token = uzivatelSerivce.authenticateAccount(
                authenticationRequest.getEmail(),
                authenticationRequest.getHeslo()
        );

        return new AuthenticationResponse(token);
    }
}
