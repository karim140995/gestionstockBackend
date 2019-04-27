package project.aiesec.gestionstock.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
		
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="label")
	private String label;
	
	@Column(name="price")
	private float price;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",nullable= true, insertable = true, updatable = true)
	private Category category;
    
    
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
