package de.ait.mp.service;

import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.dto.product.NewProductDto;

import java.util.List;

public interface ProductsService {
    ProductDto addProduct(NewProductDto newProduct);

    List<ProductDto> getAllProducts();

    ProductDto getProduct(Long id);
}
