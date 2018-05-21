package rs.ac.ni.pmf.marko.comics.server.datamodel;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
public class Publisher
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Version
	private int version;

	@Column(name = "name", length = 200, nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	private Set<ComicBook> comicBooks;
}
