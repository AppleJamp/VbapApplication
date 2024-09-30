package com.example.r23167vbap.controller;

import com.example.r23167vbap.model.AuthenticationRequest;
import com.example.r23167vbap.model.AuthenticationResponse;
import com.example.r23167vbap.model.dto.user.UserPostDTO;
import com.example.r23167vbap.model.dto.user.UserGetDTO;
import com.example.r23167vbap.model.entity.User;
import com.example.r23167vbap.service.UserService;
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
@RequestMapping("/user")
@Tag(name = "User", description = "API for User")
public class UserController {
    private final UserService userService;
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
    public UserGetDTO registerAccount(@Valid @RequestBody UserPostDTO userPostDTO) {
        return modelMapper.map(
                userService.createAccount(
                        modelMapper.map(userPostDTO, User.class)
                ), UserGetDTO.class
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

        String token = userService.authenticateAccount(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );

        return new AuthenticationResponse(token);
    }
}
