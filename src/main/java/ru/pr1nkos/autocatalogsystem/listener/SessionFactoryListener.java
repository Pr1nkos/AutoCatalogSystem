package ru.pr1nkos.autocatalogsystem.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.pr1nkos.autocatalogsystem.model.Car;


@Getter
@WebListener
public class SessionFactoryListener implements ServletContextListener {

	private SessionFactory sessionFactory;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			sessionFactory = new Configuration()
					.addAnnotatedClass(Car.class)
					.buildSessionFactory();
		} catch (HibernateException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

}
