package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AllCars")
public class AllCars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			List<Car> cars = getCarsFromDatabase();
			out.println("<html><body>");
			out.println("<h1>Список машин:</h1>");
			out.println("<div style=\"display: flex; flex-wrap: wrap;\">"); // Форматирование в виде блоков

			for (Car car : cars) {
				out.println("<div class=\"car\">");
				out.println("<img src=\"" + car.getImageURL() + "\" alt=\"" + car.getBrand() + " " + car.getModel() + "\">");
				out.println("<p><strong>" + car.getBrand() + " " + car.getModel() + "</strong></p>");
				out.println("<p>Дата производства: " + car.getProductionDate() + "</p>");
				out.println("<p>Цена: " + car.getPrice() + " руб.</p>");
				out.println("<p>Тип: " + car.getType() + "</p>");
				out.println("<p>Страна производитель: " + car.getCountry() + "</p>");
				out.println("</div>");
			}

			out.println("</div>"); // Закрытие блока flex
			out.println("</body></html>");
		} catch (HibernateException e) {
			e.printStackTrace(out);
		} finally {
			out.close();
		}
	}


	private List<Car> getCarsFromDatabase() {
		try (SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Car.class).buildSessionFactory()) {
			try (Session session = sessionFactory.openSession()) {
				return session.createQuery("SELECT c from Car c ORDER BY id", Car.class).getResultList();
			}
		}
	}
}
