package rs.ac.ni.pmf.marko.comics.server.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ComicBook
{
	@Id
	private int id;

	@Column
	private int number;
	
	@Column
	private String title;
	
	@Column
	private String frontPageUrl;

	@OneToOne(fetch = FetchType.EAGER)
	private Publisher publisher;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Hero> heroes;
}
