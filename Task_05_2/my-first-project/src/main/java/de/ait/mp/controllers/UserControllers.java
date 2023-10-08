package de.ait.mp.controllers;

import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.service.UsersService;
import de.ait.mp.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tags(value = {
        @Tag(name = "Users")
})
public class UserControllers {
    private final UsersService usersService;


    //POST documentation
    // ********************************************************************
    @Operation(summary = "Adding new user", description = "Available to everyone. The default state is USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "user has been added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "There is already a user with this email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    // POST ******************** ***************************************

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUser){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersService.register(newUser));
    }

}
