package project.aiesec.gestionstock.controller;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.HttpStatus;
import project.aiesec.gestionstock.entity.Category;
import project.aiesec.gestionstock.entity.Product;
import project.aiesec.gestionstock.service.CategoryRepository;
import project.aiesec.gestionstock.service.ProductRepository;


@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	
	@Autowired
	CategoryRepository categoryRepository;	
	
    @GetMapping(value="/product")
    @CrossOrigin
	public List<Product> getAllProduct()  {
   	 return this.productRepository.findAll();	 
    }
   
	
    @GetMapping(value="/product/{id}")
    @CrossOrigin
	public Product findProduct(@PathVariable("id") int id)  {
   	 Product product= this.productRepository.findById(id);
   	 return product;	 
    }
    
	@PostMapping(value="/product/category/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	@CrossOrigin
	public String addProduct(@PathVariable("id") int id, @RequestBody Product product)
	{
      	Category categoryfound= this.categoryRepository.findById(id);
      	if(categoryfound == null){
      		return "Category Not found";
      	}
      	product.setCategory(categoryfound);
		productRepository.save(product);
		return "New product have been added";
	}
    

    @PutMapping(value = "/product/{id}")
    @ResponseStatus(HttpStatus.OK)
	@CrossOrigin
    public String updateProduct(@PathVariable( "id" ) int id, @RequestBody Product product) {
      	Product productfound= this.productRepository.findById(id);
      	if(productfound == null){
      		return "Product Not found";
      	}
      	Category categoryfound= this.categoryRepository.findById(product.getCategory().getId());
      	if(categoryfound == null){
      		return "Category Not found";
      	}
      	productfound.setPrice(product.getPrice());
      	productfound.setLabel(product.getLabel());
      	productfound.setCategory(categoryfound);
    	this.productRepository.save(productfound);
		return "Product has been modified";
    }
    
    @DeleteMapping(value = "/product/{id}")
    @ResponseStatus(HttpStatus.OK)
	@CrossOrigin
    public void deleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);
    }
}
