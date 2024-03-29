package ru.pr1nkos.autocatalogsystem;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "Car")
@Table(name = "cars")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "production_date")
	private LocalDate productionDate;

	@Column(name = "price")
	private double price;

	@Column(name = "type")
	private String type;

	@Column(name = "country")
	private String country;

	@Column(name = "imageurl")
	private String imageURL;

	// Геттеры и сеттеры
	public Integer getId() {
		return id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	// Конструкторы
	public Car() {
	}

	public Car(String brand, String model, LocalDate productionDate, double price, String type, String country) {
		this.brand = brand;
		this.model = model;
		this.productionDate = productionDate;
		this.price = price;
		this.type = type;
		this.country = country;
	}

}
