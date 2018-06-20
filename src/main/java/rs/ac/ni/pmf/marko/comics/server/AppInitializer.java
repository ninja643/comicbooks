package rs.ac.ni.pmf.marko.comics.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import rs.ac.ni.pmf.marko.comics.server.config.ApplicationConfiguration;

public class AppInitializer implements WebApplicationInitializer
{

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException
	{
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfiguration.class);

		final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
				new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/services/rest/*");
	}

}
