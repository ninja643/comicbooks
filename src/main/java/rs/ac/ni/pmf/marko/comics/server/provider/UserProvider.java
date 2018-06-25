package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface UserProvider {

	public Iterable<UserEntity> getAll();
	
	public UserEntity get(Long id) throws ResourceNotFoundException;
	
	public UserEntity add(UserEntity user) throws DuplicateResourceException;
	
	public UserEntity updateUser(Long id, UserEntity userEntity) throws ResourceNotFoundException;
	
	public void deleteUser(Long id) throws ResourceNotFoundException;
	
}
