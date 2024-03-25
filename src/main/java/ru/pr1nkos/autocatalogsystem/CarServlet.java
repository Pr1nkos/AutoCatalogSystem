package ru.pr1nkos.autocatalogsystem;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static java.lang.System.out;

@WebServlet("/CarServlet")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try (PrintWriter out = response.getWriter()) {

			dataFromDB(out);
		} catch (IOException e) {
			out.println("Ошибка: " + e.getMessage());
		}
	}

	private static void dataFromDB(PrintWriter out) {
		try {
			// Устанавливаем соединение с базой данных
			String url = "jdbc:postgresql://localhost:5432/auto_db";
			String username = "postgres";
			String password = "1212";
			try (Connection conn = DriverManager.getConnection(url,
					username,
					password)) {

				// Создаем запрос к базе данных
				try (Statement stmt = conn.createStatement()) {
					String sql = "SELECT * FROM cars ORDER BY id";
					ResultSet rs = stmt.executeQuery(sql);

					// Выводим HTML таблицу с данными о машинах
					out.println("<html><body>");
					out.println("<h2>Таблица с данными о машинах:</h2>");
					out.println("<table border='1'><tr><th>ID</th><th>Марка</th><th>Модель</th><th>Дата производства</th><th>Цена</th><th>Тип</th><th>Страна</th></tr>");
					while (rs.next()) {
						out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>%n", rs.getInt("id"), rs.getString("brand"), rs.getString("model"), rs.getDate("production_date"), rs.getDouble("price"), rs.getString("type"), rs.getString("country"));
					}
					out.println("</table>");
					out.println("</body></html>");
					rs.close();
				}
			}
		} catch (SQLException e) {
			out.println("Ошибка: " + e.getMessage());
		}
	}
}