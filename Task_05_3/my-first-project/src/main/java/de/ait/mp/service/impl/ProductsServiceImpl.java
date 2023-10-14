package de.ait.mp.service.impl;

import de.ait.mp.dto.product.ProductDto;
import de.ait.mp.dto.product.NewProductDto;
import de.ait.mp.exceptions.RestException;
import de.ait.mp.models.Product;
import de.ait.mp.repositories.ProductsRepository;
import de.ait.mp.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Override
    public ProductDto addProduct(NewProductDto newProduct) {
            Product product = Product.builder()
                    .productName(newProduct.getProductName())
                    .categoryId(newProduct.getCategoryId())
                    .expirationDate(LocalDate.parse(newProduct.getExpirationDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                    .description(newProduct.getDescription())
                    .price(newProduct.getPrice())
                    .state(Product.State.DRAFT)
                    .build();

            productsRepository.save(product);
            return ProductDto.from(product);

    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productsRepository.findAll();
        return ProductDto.from(products);
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productsRepository.findById(id)
                .orElseThrow(()-> new RestException(HttpStatus.NOT_FOUND,"product with id " + id + " not found"));
        return ProductDto.from(product);
    }
}
