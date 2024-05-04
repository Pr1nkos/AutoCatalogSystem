package ru.pr1nkos.autocatalogsystem.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.pr1nkos.autocatalogsystem.config.LiquibaseConfig;
import ru.pr1nkos.autocatalogsystem.dao.CarDAO;
import ru.pr1nkos.autocatalogsystem.model.Car;
import ru.pr1nkos.autocatalogsystem.service.CarService;

@Getter
@WebListener
public class SessionFactoryListener implements ServletContextListener {

	@Override
	@SneakyThrows
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();

		SessionFactory sessionFactory = new Configuration()
				.addAnnotatedClass(Car.class)
				.buildSessionFactory();
		servletContext.setAttribute("sessionFactory", sessionFactory);


		CarDAO carDAO = new CarDAO(sessionFactory);
		CarService carService = new CarService(carDAO);
		servletContext.setAttribute("carDAO", carDAO);
		servletContext.setAttribute("carService", carService);


		LiquibaseConfig liquibaseConfig = new LiquibaseConfig();
		liquibaseConfig.runLiquibaseUpdate();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SessionFactory sessionFactory = (SessionFactory) sce
				.getServletContext()
				.getAttribute("sessionFactory");
		sessionFactory.close();
	}
}
