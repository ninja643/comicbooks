package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.comics.server")
@EnableWebMvc
public class ApplicationConfiguration
{
}
