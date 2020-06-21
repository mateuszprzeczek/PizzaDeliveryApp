package pl.mateuszprzeczek.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredient {
	  @Id
	  private  String id;
	  private  String name;
	  private  Type type;
	  private  Size size;
	  private double price;
	  
	  public static enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	  }
	  public static enum Size {
		  SMALL("32cm"), MEDIUM("40cm"), LARGE("50cm"), XLARGE("60cm")
	  
	  private final String description;
	  private Size(String description) {
		  this.description = description;
	  }
	 }

	public Ingredient() {
		this.type = null;
		// TODO Auto-generated constructor stub
	}

	public Ingredient(String id, String name) {
		this.type = null;
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	  
	  

}
