package rs.ac.ni.pmf.marko.comics.server.model.converter;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.UserDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;

@Component
public class UserConverter
{
	public UserDTO dtoFromEntity(final UserEntity userEntity)
	{
		return UserDTO.builder()
				.id(userEntity.getId())
				.firstName(userEntity.getFirstName())
				.lastName(userEntity.getLastName())
				.username(userEntity.getUsername())
				.email(userEntity.getEmail())
				.build();
	}
}
