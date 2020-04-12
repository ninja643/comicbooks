package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.UserConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.repository.UserRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;

@Component
public class UserProviderImpl implements UserProvider
{
	@Autowired
	private UserRepository _userRepository;

	@Autowired
	private UserConverter _userConverter;

	@Override
	public Iterable<UserDTO> getAll()
	{
		final List<UserEntity> entities = _userRepository.findAll();
		return entities.stream().map(e -> _userConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public UserDTO get(final Long id) throws ResourceNotFoundException
	{
		final UserEntity entity = _userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.USER, "User with id: " + id + " doesn't exist."));

		return _userConverter.dtoFromEntity(entity);
	}

	@Override
	public Page<UserEntity> search(final String firstName, final String lastName, final String username,
			final String password, final String email, final int pageNumber, final int pageSize)
	{
		return _userRepository.getByProperties(firstName, lastName, username, password, email,
				PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "firstName"));
	}

	@Override
	public UserEntity add(final UserEntity user) throws DuplicateResourceException
	{
		try
		{
			return _userRepository.save(user);
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.USER, "User " + user.getId() + " already exists");
		}
	}

	@Override
	public UserEntity updateUser(final Long id, final UserEntity userEntity) throws ResourceNotFoundException

	{
		throwIfUnknownId(id);

		userEntity.setId(id);

		return _userRepository.save(userEntity);
	}

	@Override
	public void deleteUser(final Long id) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);

		_userRepository.deleteById(id);
	}

	private void throwIfUnknownId(final Long id) throws ResourceNotFoundException
	{
		if (!_userRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.USER, "User with id " + id + " does not exist");
		}
	}

}
