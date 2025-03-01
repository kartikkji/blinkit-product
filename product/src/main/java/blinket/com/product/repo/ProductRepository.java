package blinket.com.product.repo;


import blinket.com.product.entity.Product;
import blinket.com.product.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Page<Product> findByCategory(ProductCategory category, Pageable pageable);

}
