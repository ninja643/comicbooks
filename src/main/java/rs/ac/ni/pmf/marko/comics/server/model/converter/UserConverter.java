package rs.ac.ni.pmf.marko.comics.server.model.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;

//@Component
@Deprecated
/**
 * @deprecated To be completely rewritten
 */
public class UserConverter
{
	public UserDTO dtoFromEntity(final UserEntity userEntity)
	{
		return new UserDTO(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
				userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
	}
}
