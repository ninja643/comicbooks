package rs.ac.ni.pmf.marko.comics.server.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class ComicBook
{
	@Id
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	private Publisher publisher;

	@OneToMany
	private List<Hero> heroes;

	@Column
	private String frontPageUrl;
}
