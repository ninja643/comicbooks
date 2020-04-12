package rs.ac.ni.pmf.marko.comics.server.model.api;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class UserDTO
{
	private Long id;

	private String firstName;

	private String lastName;

	private String username;

	private String password;

	private String email;
}
