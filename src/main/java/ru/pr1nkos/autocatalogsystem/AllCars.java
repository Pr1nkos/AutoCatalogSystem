package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.System.out;

@WebServlet("/AllCars")
public class AllCars extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AllCars.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			showCarsFromDb(out);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	private static void showCarsFromDb(PrintWriter out) {
		try {
			// Создаем фабрику сессий Hibernate и открываем сессию
			SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Car.class).buildSessionFactory();
			try (Session session = sessionFactory.openSession()) {
				List<Car> cars = session.createQuery("SELECT c from Car c", Car.class).getResultList();
				out.println("<html><body>");
				out.println("<h1>Список машин:</h1>");
				out.println("<ul>");
				for (Car car : cars) {
					out.println("<li>" + car.getBrand() + " " + car.getModel() + "</li>");
				}
				out.println("</ul>");
				out.println("</body></html>");

			}
			sessionFactory.close();
		} catch (HibernateException e) {
			logger.info("Hibernate Exception");
		}
	}
}