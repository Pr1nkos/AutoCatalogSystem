package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException {
		int carId = Integer.parseInt(request.getParameter("carId"));
		SessionFactory sessionFactory =
				new Configuration().addAnnotatedClass(Car.class).buildSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Car car = session.get(Car.class, carId);
			if (car != null) {
				String imagePath = getServletContext().getRealPath("/resources/images/") + car.getImageURL();
				Path path = Paths.get(imagePath);
				Files.deleteIfExists(path);
				session.remove(car);
				transaction.commit();
				response.getWriter().println("Машина и изображение успешно удалены из базы данных.");
			}
			else {
				response.getWriter().println("Машина с указанным идентификатором не найдена.");
			}
		}
		sessionFactory.close();
		response.sendRedirect("index.jsp");
	}
}
