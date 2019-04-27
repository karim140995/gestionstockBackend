package project.aiesec.gestionstock.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.aiesec.gestionstock.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	  Category findById(int primaryKey);
	  
	  @Query("SELECT c FROM Category c WHERE c.label like %:filter%")
	  List<Category> findCategoryByLabel(
			  @Param("filter") String filter);
	
}
