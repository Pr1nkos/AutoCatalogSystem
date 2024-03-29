package ru.pr1nkos.autocatalogsystem;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebFilter("/images/*")
public class ImagesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Получить путь к запрашиваемому ресурсу
		String path = ((HttpServletRequest) request).getRequestURI();

		// Настроить поток ответа для передачи ресурса
		ServletContext context = request.getServletContext();
		String fullPath = context.getRealPath(path);
		File file = new File(fullPath);

		// Если файл существует, отправить его клиенту
		if (file.exists()) {
			chain.doFilter(request, response);
		}
		else {
			// В противном случае, вернуть HTTP 404
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	// Реализация методов интерфейса Filter (init и destroy) может быть пустой
}
