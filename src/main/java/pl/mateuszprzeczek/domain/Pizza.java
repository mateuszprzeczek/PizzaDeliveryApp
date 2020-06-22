package pl.mateuszprzeczek.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Pizza {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  
	  @NotNull
	  @Size(min=4, message="Name must be at least 4 characters long")
	  private String name;
	  private  PizzaSize size;
	  private  double price;
	  
	  private Date createdAt;

	  @ManyToMany(targetEntity=Ingredient.class)
	  @Size(min=1, message="You must choose at least 1 ingredient")
	  private List<Ingredient> ingredients;

	  @PrePersist
	  void createdAt() {
	    this.createdAt = new Date();
	  }
	  
	  public static enum PizzaSize {
		  SMALL("32cm", 0.0), MEDIUM("40cm", 0.25), LARGE("50cm", 0.50), XLARGE("60cm", 0.60);
	  
	  private final String description;
	  private final double price;

	  private PizzaSize(String description, double price) {
		  this.description = description;
		  this.price = price;
	  }
	public String getDescription() {
		return description;
	}
	public static PizzaSize fromDescription(String description) {
        PizzaSize[] values = values();
        for (PizzaSize size : values) {
            if (size.getDescription().equals(description))
                return size;
        }
        return null;
    }
	 }

	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pizza(Long id, @NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String name,
			Date createdAt,
			@Size(min = 1, message = "You must choose at least 1 ingredient") List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.ingredients = ingredients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	  
	  

}
