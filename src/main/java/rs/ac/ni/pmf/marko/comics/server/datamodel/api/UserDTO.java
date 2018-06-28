package rs.ac.ni.pmf.marko.comics.server.datamodel.api;

import lombok.Value;

@Value
public class UserDTO 
{
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private String password;
	
	private String email;
}
