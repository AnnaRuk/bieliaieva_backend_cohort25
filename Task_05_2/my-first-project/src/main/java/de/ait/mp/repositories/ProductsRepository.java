package de.ait.mp.repositories;

import de.ait.mp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product,Long> {
}
