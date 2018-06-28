package rs.ac.ni.pmf.marko.comics.server.provider;

import org.springframework.data.domain.Page;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface UserProvider 
{
	public Iterable<UserDTO> getAll();
	
	public UserDTO get(Long id) throws ResourceNotFoundException;

	public Page<UserEntity> search(String firstName, String lastName, String username, String password, String email, int pageNumber, int pageSize);
	
	public UserEntity add(UserEntity user) throws DuplicateResourceException;
	
	public UserEntity updateUser(Long id, UserEntity userEntity) throws ResourceNotFoundException;
	
	public void deleteUser(Long id) throws ResourceNotFoundException;
	
}
