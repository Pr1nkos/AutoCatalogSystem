package ru.pr1nkos.autocatalogsystem.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebFilter("/images/*")
public class ImagesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getRequestURI();
		ServletContext context = request.getServletContext();
		String fullPath = context.getRealPath(path);
		File file = new File(fullPath);
		if (file.exists()) {
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
