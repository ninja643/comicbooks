package rs.ac.ni.pmf.marko.comics.server.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Value;

@Value
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;

	@OneToMany
	private List<ComicBook> comicBooks;
}
