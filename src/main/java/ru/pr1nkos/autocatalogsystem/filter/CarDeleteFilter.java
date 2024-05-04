package ru.pr1nkos.autocatalogsystem.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.pr1nkos.autocatalogsystem.service.CarService;

import java.io.IOException;

@WebFilter("/controller/CarController")
public class CarDeleteFilter implements Filter {

	private CarService carService;

	@Override
	public void init(FilterConfig filterConfig) {
		ServletContext servletContext = filterConfig.getServletContext();
		carService = (CarService) servletContext.getAttribute("carService");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String action = httpRequest.getParameter("action");

		if ("delete".equals(action)) {
			String carId = httpRequest.getParameter("carId");
			if (carId != null) {
				carService.deleteCar(httpRequest, httpResponse);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
