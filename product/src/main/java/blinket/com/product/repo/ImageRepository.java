package blinket.com.product.repo;


import blinket.com.product.entity.ProductImage;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProductId(Integer id);
}
