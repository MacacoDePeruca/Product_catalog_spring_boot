package com.br.vitornascimento.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.vitornascimento.data.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p "
			+ "WHERE (p.name LIKE LOWER(CONCAT('%',:name_or_description,'%')) OR p.description LIKE LOWER(CONCAT('%',:name_or_description,'%'))) "
			+ "AND p.price BETWEEN :min_price AND :max_price"
			)	
	List<Product> searchProducts(
			@Param("name_or_description") String nameOrDescription,
			@Param("min_price") BigDecimal minPrice,
			@Param("max_price") BigDecimal maxPrice
			);
	
	

}
