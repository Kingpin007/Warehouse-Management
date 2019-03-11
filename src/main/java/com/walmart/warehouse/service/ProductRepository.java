package com.walmart.warehouse.service;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.walmart.warehouse.domain.ProductDO;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductDO, UUID>{
	
	@Query(value = "SELECT * FROM PRODUCT WHERE PRODUCT_KEY = ?0", nativeQuery = true)
	public ProductDO findProductDOByProductKey(Integer productKey);
}
