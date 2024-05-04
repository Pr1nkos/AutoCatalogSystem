package ru.pr1nkos.autocatalogsystem.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.pr1nkos.autocatalogsystem.model.Car;

import java.util.List;

@AllArgsConstructor
public class CarDAO {
	private SessionFactory sessionFactory;

	public void addCar(Car car) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(car);
			transaction.commit();
		}
	}

	public void deleteCar(int carId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Car car = session.get(Car.class, carId);
			if (car != null) {
				session.remove(car);
				transaction.commit();
			}
		}
	}

	public List<Car> getAllCars() {
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery("SELECT c from Car c ORDER BY id", Car.class).getResultList();
		}
	}
}
