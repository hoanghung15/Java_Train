package org.example.ex4.repository;

import org.example.ex4.entity.Category;
import org.example.ex4.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    @Query(
//            value = "SELECT * FROM product WHERE (:devideName IS NULL OR name = :devideName)",
//            nativeQuery = true
//    )

    @Query("""
            select p from Product p  where (:devideName is null or p.name =:devideName)
            """)

    Page<Product> findAllProductWithName(String devideName, Pageable pageable);


}
