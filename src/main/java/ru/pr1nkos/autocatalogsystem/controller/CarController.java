package ru.pr1nkos.autocatalogsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.pr1nkos.autocatalogsystem.service.CarService;

import java.io.IOException;

@WebServlet("/controller/CarController")
@MultipartConfig
public class CarController extends HttpServlet {

	private CarService carService;

	@Override
	public void init() throws ServletException {
		super.init();
		carService = (CarService) getServletContext().getAttribute("carService");
	}


	private static final String INDEX = "../index.jsp";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("add")) {
			carService.addCar(request, response);
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
