package rs.ac.ni.pmf.marko.comics.server.api;

import lombok.Value;
import rs.ac.ni.pmf.marko.comics.server.datamodel.UserEntity;

@Value
public class UserDTO {

	private Long id;
	
	private int version;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private String email;
	
	public UserDTO fromEntity(UserEntity userEntity)
	{
		return new UserDTO(userEntity.getId(), userEntity.getVersion(), userEntity.getFirstName(),
				userEntity.getLastName(), userEntity.getUsername(),
				userEntity.getPassword(), userEntity.getEmail());
	}
	
	public UserEntity toEntity() 
	{
		return new UserEntity(getId(), getVersion(), getFirstName(),
				getLastName(), getUsername(), getPassword(), getEmail());
	}
	
}
