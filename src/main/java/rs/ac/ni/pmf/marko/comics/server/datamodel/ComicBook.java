package rs.ac.ni.pmf.marko.comics.server.datamodel;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(
	uniqueConstraints = {
		@UniqueConstraint(columnNames = { "number", "publisher_id" })
	})
public class ComicBook
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Version
	private int version;

	@Column
	private int number;

	@Column
	private String title;

	@Column
	private String frontPageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	private Publisher publisher;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Hero> heroes;
}
