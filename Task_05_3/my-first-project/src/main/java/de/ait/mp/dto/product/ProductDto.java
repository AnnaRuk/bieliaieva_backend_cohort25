package de.ait.mp.dto.product;

import de.ait.mp.models.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Product", description = "information about product")
public class ProductDto {

    @Schema(description = "Product identifier", example = "1")
    private Long id;
    @Schema(description = "Product name", example = "Pupa")
    private String productName;
    @Schema(description = "Identifier of a product category", example = "1")
    private int categoryId;
    @Schema(description = "use by the time", example = "23.07.2025")
    private String expirationDate;
    @Schema(description = "Product description", example = "Toy doll for kids, lifelike, collectible, various styles, sizes, and materials")
    private String description;
    @Schema(description = "price", example = "99.99")
    private Double price;
    @Schema(description = "State of product - DRAFT,AVAILABLE, TEMPORARILY_UNAVAILABLE, OUTDATED", example = "DRAFT")
    private String state;

    public static ProductDto from (Product product){
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .categoryId(product.getCategoryId())
                .expirationDate(product.getExpirationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .description(product.getDescription())
                .price(product.getPrice())
                .state(product.getState().toString())
                .build();
    }

    public static List<ProductDto> from (List<Product> products){
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

 /*
 "id":1,
 "productName": "new product",
"categoryID": 2,
"expirationDate": "02.02.2024",
"description": "Description of the new product",
"price": 100.0
"state": "DRAFT"
    */
}
