package project.aiesec.gestionstock.service;

import org.springframework.data.jpa.repository.JpaRepository;

import project.aiesec.gestionstock.entity.Category;
import project.aiesec.gestionstock.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	  Product findById(int primaryKey);

}
