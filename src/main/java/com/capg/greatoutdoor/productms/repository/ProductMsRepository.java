package com.capg.greatoutdoor.productms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.greatoutdoor.productms.model.ProductDto;
/*It allows us to access and persist data between Java object/class and relational database and provides Entity Manager API ,provides CRUD operations */
public interface ProductMsRepository extends JpaRepository<ProductDto, String> { /* to achieve the abstarction */
public List<ProductDto> findByProductName(String ProductName);/*It is a ordered collection it conatins index based methods */
public ProductDto findByManufacture(String manufacture);
public boolean existsByProductName(String productName);
}