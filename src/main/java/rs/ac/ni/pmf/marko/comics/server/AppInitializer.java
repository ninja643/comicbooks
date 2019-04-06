package rs.ac.ni.pmf.marko.comics.server;

import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import rs.ac.ni.pmf.marko.comics.server.config.ApplicationConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer
{

	@Override
	public void onStartup(@NonNull final ServletContext servletContext)
	{
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfiguration.class);

		final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
				new DispatcherServlet(context));
		registration.setLoadOnStartup(1);

		registration.addMapping("/swagger-resources/configuration/ui");
		registration.addMapping("/swagger-resources/configuration/security");
		registration.addMapping("/swagger-resources");
		registration.addMapping("/v2/api-docs");
		registration.addMapping("/services/rest/*");
	}

}
