package rs.ac.ni.pmf.marko.comics.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.UserConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

//@Service
@RequiredArgsConstructor
@Deprecated
/**
 * @deprecated To be completely rewritten
 */
public class UsersService
{
	private final UsersRepository _usersRepository;
	private final UserConverter _userConverter;

	public Iterable<UserDTO> getAll()
	{
		final List<UserEntity> entities = _usersRepository.findAll();
		return entities.stream().map(e -> _userConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	public UserDTO get(final Long id) throws ResourceNotFoundException
	{
		final UserEntity entity = _usersRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.USER, "User with id: " + id + " doesn't exist."));

		return _userConverter.dtoFromEntity(entity);
	}

	public Page<UserEntity> search(final String firstName, final String lastName, final String username,
								   final String password, final String email, final int pageNumber, final int pageSize)
	{
		return _usersRepository.getByProperties(firstName, lastName, username, password, email,
												PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "firstName"));
	}

	public UserEntity add(final UserEntity user) throws DuplicateResourceException
	{
		try
		{
			return _usersRepository.save(user);
		}
		catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.USER, "User " + user.getId() + " already exists");
		}
	}

	public UserEntity updateUser(final Long id, final UserEntity userEntity) throws ResourceNotFoundException

	{
		throwIfUnknownId(id);

		userEntity.setId(id);

		return _usersRepository.save(userEntity);
	}

	public void deleteUser(final Long id) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);

		_usersRepository.deleteById(id);
	}

	private void throwIfUnknownId(final Long id) throws ResourceNotFoundException
	{
		if (!_usersRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.USER, "User with id " + id + " does not exist");
		}
	}
}
