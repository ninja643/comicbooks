package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Builder
@Entity
public class UserEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@OneToMany(mappedBy = "user")
	private List<UserComicBookEntity> comicBooks;
}
