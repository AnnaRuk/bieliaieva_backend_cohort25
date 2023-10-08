package de.ait.mp.controllers;

import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.dto.product.NewProductDto;
import de.ait.mp.dto.StandardResponseDto;
import de.ait.mp.service.ProductsService;
import de.ait.mp.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
@Tags(value = {
        @Tag(name = "Products")
})
public class ProductsController {

    private final ProductsService productsService;
//POST documentation
   // ********************************************************************
    @Operation(summary = "Adding new product", description = "Available to everyone. The default state is DRAFT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "product has been added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "There is already a product with this productName",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))),
    })
    // POST ******************** ***************************************
@PostMapping
//@ResponseStatus(HttpStatus.CREATED) ///return 201
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid NewProductDto newProduct){
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(productsService.addProduct(newProduct));

}

//GET ALL documentation
    // ********************************************************************


    @Operation(summary = "Getting a list of products", description = "Available to everyone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Products not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })

    // GET ******************** All ***************************************

@GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
    return ResponseEntity.ok() /// return 200 ResponseEntity.status(HttpStatus.OK)
                    .body(productsService.getAllProducts());


}
//GET One documentation
    // ********************************************************************


    @Operation(summary = "Getting a product", description = "Available to everyone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Request processed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Product not found",
                      content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })

    // GET ******************** One ***************************************

    @GetMapping("/{product-id}")
   public ResponseEntity<ProductDto> getProduct(
           @Parameter(description = "Product identifier", example = "1")
           @PathVariable("product-id") Long id) {
        return ResponseEntity.ok() /// return 200 ResponseEntity.status(HttpStatus.OK)
                .body(productsService.getProduct(id));

    }

}
