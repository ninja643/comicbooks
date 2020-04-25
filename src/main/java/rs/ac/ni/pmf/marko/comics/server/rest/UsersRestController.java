package rs.ac.ni.pmf.marko.comics.server.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.service.UsersService;

import java.security.Principal;
import java.util.Collection;

//@RestController
//@Api
//@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Deprecated
/**
 * @deprecated To be completely rewritten
 */
public class UsersRestController
{
	UsersService _usersService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<UserDTO> getAll()

	{
		return _usersService.getAll();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public Iterable<UserEntity> search(@RequestParam(name = "firstName", required = false) final String firstName,
									   @RequestParam(name = "lastName", required = false) final String lastName,
									   @RequestParam(name = "username", required = false) final String username,
									   @RequestParam(name = "password", required = false) final String password,
									   @RequestParam(name = "email", required = false) final String email,
									   @RequestParam(name = "page") final int pageNumber, @RequestParam(name = "pageSize") final int pageSize)
	{
		return _usersService.search(firstName, lastName, username, password, email, pageNumber, pageSize);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserDTO getById(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException

	{
		return _usersService.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity add(@RequestBody final UserEntity user) throws DuplicateResourceException
	{
		return _usersService.add(user);
	}

	@RequestMapping(value = "/update/{id}",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity update(@PathVariable(name = "id") final Long id, @RequestBody final UserEntity userEntity)
			throws ResourceNotFoundException

	{
		return _usersService.updateUser(id, userEntity);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException
	{
		_usersService.deleteUser(id);
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
