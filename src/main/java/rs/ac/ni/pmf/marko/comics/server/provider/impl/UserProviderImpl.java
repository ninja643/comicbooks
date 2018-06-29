package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.UserConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.UserEntity;

import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.UserRepository;
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
		List<UserEntity> entities = _userRepository.findAll();
		return entities.stream().map(e -> _userConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public UserDTO get(Long id) throws ResourceNotFoundException
	{
		UserEntity entity = _userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.USER,
						"User with id: "+id+" doesn't exist."));
		
		return _userConverter.dtoFromEntity(entity);
	}
	
	@Override
	public Page<UserEntity> search(String firstName, String lastName, String username, String password,
			String email, int pageNumber, int pageSize) 
	{
		return _userRepository.getByProperties(firstName, lastName, username, password, email,
				PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "firstName"));
	}
	
	@Override
	public UserEntity add(UserEntity user) throws DuplicateResourceException 
	{
		try 
		{
		return _userRepository.save(user);
		}catch(final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.USER, 
				"User "+user.getId()+" already exists");
		}
	}

	@Override
	public UserEntity updateUser(Long id, UserEntity userEntity) throws ResourceNotFoundException 

	{
		throwIfUnknownId(id);
		
		userEntity.setId(id);
		
		return _userRepository.save(userEntity);
	}

	@Override
	public void deleteUser(Long id) throws ResourceNotFoundException {
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
