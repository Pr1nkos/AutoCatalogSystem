package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/AddCarServlet")
@MultipartConfig
public class AddCarServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, RuntimeException {
		// Получаем параметры из формы
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		Part imagePart = request.getPart("image");
		String imageName = "car_" + System.currentTimeMillis() + ".jpg"; // Генерируем уникальное имя файла
		String imagePath = getServletContext().getRealPath("/resources/images/") + imageName; // Полный путь к папке для сохранения изображений на сервере

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate productionDate;
		try {
			productionDate = LocalDate.parse(request.getParameter("productionDate"), dateFormat);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		try (InputStream inputStream = imagePart.getInputStream();
		     OutputStream outputStream = new FileOutputStream(imagePath)) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}
		String imageURL = request.getContextPath() + "/resources/images/" + imageName;
		double price = Double.parseDouble(request.getParameter("price"));
		String type = request.getParameter("type");
		String country = request.getParameter("country");

		// Создаем объект машины
		Car car = new Car(brand, model, productionDate, price, type, country);
		car.setImageURL(imageURL);
		// Сохраняем машину в базе данных с помощью Hibernate
		try {
			SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Car.class).buildSessionFactory();
			try (Session session = sessionFactory.openSession()) {
				Transaction transaction = session.beginTransaction();
				session.save(car);
				transaction.commit();
			}
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
			// В случае ошибки отправляем пользователю сообщение об ошибке
			response.getWriter().println("Ошибка при добавлении машины в базу данных.");
		}
		response.sendRedirect("index.jsp");
	}


}