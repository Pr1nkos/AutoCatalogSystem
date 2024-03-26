package ru.pr1nkos.autocatalogsystem;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cars")
@NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "production_date")
	private Date productionDate;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "type")
	private String type;

	@Column(name = "country")
	private String country;

	// Геттеры и сеттеры
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

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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
	public Car() {}

	public Car(String brand, String model, Date productionDate, BigDecimal price, String type, String country) {
		this.brand = brand;
		this.model = model;
		this.productionDate = productionDate;
		this.price = price;
		this.type = type;
		this.country = country;
	}

}
