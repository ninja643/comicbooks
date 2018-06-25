package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import rs.ac.ni.pmf.marko.comics.server.datamodel.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.UserRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;

public class UserProviderImpl implements UserProvider {

	@Autowired
	private UserRepository _userRepository;
	
	@Override
	public Iterable<UserEntity> getAll() 
	{
		return _userRepository.findAll();
	}

	@Override
	public UserEntity get(Long id) throws ResourceNotFoundException
	{
		return _userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.USER,
						"User with id: "+id+"doesn't exist."));
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
					"User "+user.getFirstName()+" already exists");
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
