package rs.ac.ni.pmf.marko.comics.server.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserProvider userProvider;
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	String[] publicMatchers = { "**/logout",
								"**/login", 
							  };

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/*auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("user")
				.password(passwordEncoder.encode("password")).roles("ADMIN", "USER");*/

		addTestUserToDatabase();

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from UserEntity where username=?")
				.authoritiesByUsernameQuery("select username, role from UserEntity where username=?")
				.passwordEncoder(passwordEncoder);

	}

	/**
	 * Adds test user with admin privilegies, username=admin, password=admin
	 * @throws SQLException
	 * @throws DuplicateResourceException
	 */
	
	private void addTestUserToDatabase() throws SQLException, DuplicateResourceException {
		
		String encodedPassword = passwordEncoder.encode("admin");

		UserEntity testUser = new UserEntity(null, 0, "Petar", "Petrovic", "admin", encodedPassword, "1", "pera@pera.com", "ROLE_ADMIN");
		userProvider.add(testUser);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(publicMatchers).permitAll().
								 antMatchers("/services/rest/**").hasRole("ADMIN")
								 .anyRequest().authenticated().and().formLogin();

		http.csrf().disable();

	}

}
