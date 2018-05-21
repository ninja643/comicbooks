package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.ComicBookProviderImpl;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.PublisherProviderImpl;

@Configuration
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.comics.server")
@EnableWebMvc
public class ApplicationConfiguration
{
	@Bean
	public ComicBookProvider getComicBookProvider()
	{
		return new ComicBookProviderImpl();
	}

	@Bean
	public PublisherProvider getPublisherProvider()
	{
		return new PublisherProviderImpl();
	}
}
