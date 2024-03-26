package ru.pr1nkos.autocatalogsystem;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static java.lang.System.out;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {
			showCarsFromDb(out);
		}catch (Exception e){
			e.printStackTrace(out);
		}
	}

	private static void showCarsFromDb(PrintWriter out) {
		try {
			SessionFactory sessionFactory = new Configuration().buildSessionFactory();
			try (Session session = sessionFactory.openSession()) {
				List<Car> cars = session.createNamedQuery("Car.findAll", Car.class).getResultList();

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