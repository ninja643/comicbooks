package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.ComicBookProviderImpl;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.HeroProviderImpl;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.PublisherProviderImpl;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.UserProviderImpl;

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
	
	@Bean
	public UserProvider getUserProvider()
	{
		return new UserProviderImpl();
	}
	
	@Bean
	public HeroProvider getHeroProvider()
	{
		return new HeroProviderImpl();
	}
	
}
