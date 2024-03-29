package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

@WebServlet("/DeleteCarServlet")
public class DeleteCarServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Получаем идентификатор машины для удаления из параметров запроса
		int carId = Integer.parseInt(request.getParameter("carId"));

		// Удаление машины из базы данных с помощью Hibernate
		try {
			SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Car.class).buildSessionFactory();
			try (Session session = sessionFactory.openSession()) {
				Transaction transaction = session.beginTransaction();
				// Загружаем машину по идентификатору
				Car car = session.get(Car.class, carId);
				if (car != null) {
					// Если машина найдена, удаляем её
					session.remove(car);
					transaction.commit();
					response.getWriter().println("Машина успешно удалена из базы данных.");
				}
				else {
					// Если машина не найдена, отправляем сообщение об ошибке
					response.getWriter().println("Машина с указанным идентификатором не найдена.");
				}
			}
			sessionFactory.close();
		} catch (Exception e) {
			e.printStackTrace();
			// В случае ошибки отправляем пользователю сообщение об ошибке
			try {
				response.getWriter().println("Ошибка при удалении машины из базы данных.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		response.sendRedirect("index.jsp");
	}


}