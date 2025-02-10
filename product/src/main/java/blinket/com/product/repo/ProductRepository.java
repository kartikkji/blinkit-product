package blinket.com.product.repo;


import blinket.com.product.entity.Product;
import blinket.com.product.entity.ProductImage;
import blinket.com.product.enums.ProductCategory;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByCategory(ProductCategory category);

}
