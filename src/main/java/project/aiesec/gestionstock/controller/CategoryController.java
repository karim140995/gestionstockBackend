package project.aiesec.gestionstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.aiesec.gestionstock.entity.Category;
import project.aiesec.gestionstock.service.CategoryRepository;


@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
    @GetMapping(value="/category/{id}")
    @CrossOrigin
	public Category findCategory(@PathVariable("id") int id)  {
   	 Category category= this.categoryRepository.findById(id);
   	 return category;	 
    }
    
    @GetMapping(value="/category/search/{filter}")
    @CrossOrigin
	public List<Category> searchCategory(@PathVariable("filter") String filter)  {
   	 List<Category> categories= this.categoryRepository.findCategoryByLabel(filter);
   	 return categories;	 
    }
    
    @GetMapping(value="/category")
    @CrossOrigin
	public List<Category> getAllCategory()  {
   	 return this.categoryRepository.findAll();	 
    }
	
	@PostMapping(value="/category")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin
	public String addCategory(@RequestBody Category category)
	{
		categoryRepository.save(category);
		return "New category have been added";
	}
	
    @PutMapping(value = "/category/{id}")
    @ResponseStatus(HttpStatus.OK)
	@CrossOrigin
    public String updateCategory(@PathVariable( "id" ) int id, @RequestBody Category category) {
      	Category categoryfound= this.categoryRepository.findById(id);
      	if(categoryfound == null){
      		return "Category Not found";
      	}
      	categoryfound.setLabel(category.getLabel());
    	this.categoryRepository.save(categoryfound);
		return "Category has been modified";
    }
    @DeleteMapping(value = "/category/{id}")
    @ResponseStatus(HttpStatus.OK)
	@CrossOrigin
    public void deleteCategory(@PathVariable("id") int id) {
        categoryRepository.deleteById(id);
    }

}
