package ru.pr1nkos.autocatalogsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	@Column(name = "image_url")
	private String imageURL;

	public Car(String brand, String model, LocalDate productionDate, double price, String type, String country, String imageURL) {
		this.brand = brand;
		this.model = model;
		this.productionDate = productionDate;
		this.price = price;
		this.type = type;
		this.country = country;
		this.imageURL = imageURL;
	}
}
