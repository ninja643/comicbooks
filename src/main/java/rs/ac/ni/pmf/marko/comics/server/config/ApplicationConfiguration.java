package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import rs.ac.ni.pmf.marko.comics.server.provider.DataProvider;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.DataProviderStub;
import rs.ac.ni.pmf.marko.comics.server.provider.impl.DbDataProvider;

@Configuration
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.comics.server")
@EnableWebMvc
public class ApplicationConfiguration
{
	@Bean
	public DataProvider getDataProvider()
	{
//		return new DataProviderStub();
		return new DbDataProvider();
	}
}
