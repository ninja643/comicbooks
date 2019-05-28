package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "rs.ac.ni.pmf.marko.comics.server")
public class ApplicationConfiguration
{
}
