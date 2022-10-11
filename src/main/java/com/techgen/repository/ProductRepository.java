package com.techgen.repository;

import com.techgen.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT  p FROM Product p " +
            "WHERE p.name LIKE CONCAT('%', :searchStr, '%') " +
            "OR p.description LIKE CONCAT('%', :searchStr, '%')")
    Page<Product> searchProductsByNameOrDescription(String searchStr, Pageable pageable);

    @Query(value = "SELECT  * FROM products p " +
            "WHERE p.name LIKE CONCAT('%', :searchStr, '%') " +
            "OR p.description LIKE CONCAT('%', :searchStr, '%')",nativeQuery = true)
    List<Product> searchProductsByNameOrDescriptionSQL(String searchStr);
}
