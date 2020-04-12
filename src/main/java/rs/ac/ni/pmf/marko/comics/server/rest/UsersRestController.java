package rs.ac.ni.pmf.marko.comics.server.rest;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;

@RestController
@Api
@RequestMapping(value = "/user")
public class UsersRestController
{
	@Autowired
	UserProvider dataProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<UserDTO> getAll()

	{
		return dataProvider.getAll();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public Iterable<UserEntity> search(@RequestParam(name = "firstName", required = false) final String firstName,
			@RequestParam(name = "lastName", required = false) final String lastName,
			@RequestParam(name = "username", required = false) final String username,
			@RequestParam(name = "password", required = false) final String password,
			@RequestParam(name = "email", required = false) final String email,
			@RequestParam(name = "page") final int pageNumber, @RequestParam(name = "pageSize") final int pageSize)
	{
		return dataProvider.search(firstName, lastName, username, password, email, pageNumber, pageSize);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserDTO getById(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException

	{
		return dataProvider.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity add(@RequestBody final UserEntity user) throws DuplicateResourceException
	{
		return dataProvider.add(user);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity update(@PathVariable(name = "id") final Long id, @RequestBody final UserEntity userEntity)
			throws ResourceNotFoundException

	{
		return dataProvider.updateUser(id, userEntity);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException
	{
		dataProvider.deleteUser(id);
	}

	/**
	 * Returns roles of the logged in user commiting this request
	 *
	 * @param principal
	 * @throws ResourceNotFoundException
	 */

	@RequestMapping(value = "loggedInUserRoles", method = RequestMethod.GET)
	public Collection<? extends GrantedAuthority> getLogedInUsername(final Authentication authentication)
			throws ResourceNotFoundException
	{
		if (authentication == null)
		{
			throw new ResourceNotFoundException(ResourceType.USER, "Only logged in users can see their roles!");
		}

		return authentication.getAuthorities();

	}

	/**
	 * Returns username of the logged in user commiting this request
	 *
	 * @param authentication
	 * @throws ResourceNotFoundException
	 *
	 */

	@RequestMapping(value = "/loggedInUsername", method = RequestMethod.GET)
	public String getAuthoritiesFromLoggedInUser(final Principal principal) throws ResourceNotFoundException
	{
		if (principal == null)
		{
			throw new ResourceNotFoundException(ResourceType.USER, "Only logged in users can see their username!");
		}

		return principal.getName();
	}

}
