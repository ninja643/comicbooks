package rs.ac.ni.pmf.marko.comics.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.service.UsersService;

import javax.sql.DataSource;
import java.sql.SQLException;

//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DataSource dataSource;

	@Autowired
	UsersService usersService;

	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	public static final String[] PUBLIC_MATCHERS = {"/logout/**", "/login/**", "/swagger-ui.html"};

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception
	{

		/*
		 * auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).
		 * withUser("user")
		 * .password(passwordEncoder.encode("password")).roles("ADMIN", "USER");
		 */

//        addTestUserToDatabase();

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from UserEntity where username=?")
				.authoritiesByUsernameQuery("select username, role from UserEntity where username=?")
				.passwordEncoder(passwordEncoder);

	}

	/**
	 * Adds test user with admin privilegies, username=admin, password=admin
	 *
	 * @throws SQLException
	 * @throws DuplicateResourceException
	 */

//    private void addTestUserToDatabase() throws SQLException, DuplicateResourceException {
//
//        final String encodedPassword = passwordEncoder.encode("admin");
//
//        final UserEntity testUser = UserEntity.builder()
//                .version(0)
//                .role("admin")
//                .password(encodedPassword)
//                .firstName("Marko")
//                .lastName("Milosevic")
//                .email("ninja643@gmail.com")
//                .build();
//
//        usersService.add(testUser);
//
//    }
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{

		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().

				antMatchers("/services/rest/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin().and()
				.csrf().disable();

	}

}
