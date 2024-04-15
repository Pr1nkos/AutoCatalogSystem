package ru.pr1nkos.autocatalogsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.pr1nkos.autocatalogsystem.listener.SessionFactoryListener;
import ru.pr1nkos.autocatalogsystem.service.CarService;

import java.io.IOException;
import java.io.Serializable;

@WebServlet("/controller/CarController")
@MultipartConfig
public class CarController extends HttpServlet implements Serializable {
	private final transient CarService carService;

	public CarController() {
		SessionFactoryListener sessionFactoryListener = new SessionFactoryListener();
		sessionFactoryListener.contextInitialized(null);
		this.carService = new CarService(sessionFactoryListener.getSessionFactory());
	}

	private static final String INDEX = "../index.jsp";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
				case "add":
					carService.addCar(request, response);
					break;
				case "delete":
					carService.deleteCar(request, response);
					break;
				default:
					response.sendRedirect(INDEX);
			}
		}
		else {
			response.sendRedirect(INDEX);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
				case "getAll":
					carService.getAllCars(request, response);
					break;
				case "addCarForm":
					request.getRequestDispatcher("../addCar.jsp").forward(request, response);
					break;
				default:
					response.sendRedirect(INDEX);
			}
		}
		else {
			response.sendRedirect(INDEX);
		}
	}
}
