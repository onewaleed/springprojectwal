package com.example.trainers.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Trainers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//Auto Increments
	private Integer id;
	
	private String brand;
	private String model;
	private String colour;

	public Trainers() {
		super();
	}

	public Trainers(Integer id, String brand, String model, String colour) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.colour = colour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "Trainers [id=" + id + ", brand=" + brand + ", model=" + model + ", colour=" + colour + "]";
	}
	
}
