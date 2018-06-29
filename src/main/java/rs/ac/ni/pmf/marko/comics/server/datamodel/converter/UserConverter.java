package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.UserEntity;

@Component
public class UserConverter
{
	public UserDTO dtoFromEntity(UserEntity userEntity)
	{
		return new UserDTO(userEntity.getId(), userEntity.getFirstName(), userEntity.getLastName(),
				userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
	}
}