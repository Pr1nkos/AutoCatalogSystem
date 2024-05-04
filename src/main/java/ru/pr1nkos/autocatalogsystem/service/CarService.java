package ru.pr1nkos.autocatalogsystem.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import ru.pr1nkos.autocatalogsystem.dao.CarDAO;
import ru.pr1nkos.autocatalogsystem.model.Car;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@AllArgsConstructor
public class CarService {
	private CarDAO carDAO;


	public void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Car car = createCarFromRequest(request);
		saveImageFromRequest(request, car.getModel());
		carDAO.addCar(car);
		response.sendRedirect("../index.jsp");
	}

	public void deleteCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int carId = Integer.parseInt(request.getParameter("carId"));
		carDAO.deleteCar(carId);
		response.sendRedirect("../index.jsp");
	}

	public void getAllCars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Car> cars = carDAO.getAllCars();
		request.setAttribute("cars", cars);
		request.getRequestDispatcher("../allCars.jsp").forward(request, response);
	}


	private Car createCarFromRequest(HttpServletRequest request) {
		return Car.builder()
				.brand(request.getParameter("brand"))
				.model(request.getParameter("model"))
				.productionDate(LocalDate.parse(request.getParameter("productionDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.price(Double.parseDouble(request.getParameter("price")))
				.type(request.getParameter("type"))
				.country(request.getParameter("country"))
				.imageURL("../resources/images/" + request.getParameter("model") + ".jpg")
				.build();
	}

	private void saveImageFromRequest(HttpServletRequest request, String modelName) throws IOException, ServletException {
		Part imagePart = request.getPart("image");
		String imageName = modelName + ".jpg";
		String imagePath = request.getServletContext().getRealPath("/resources/images/") + imageName;
		try (InputStream inputStream = imagePart.getInputStream()) {
			try (OutputStream outputStream = new FileOutputStream(imagePath)) {
				byte[] buffer = new byte[4096];
				int bytesRead;
				while (-1 != (bytesRead = inputStream.read(buffer)))
					outputStream.write(buffer, 0, bytesRead);
			}
		}
	}
}
