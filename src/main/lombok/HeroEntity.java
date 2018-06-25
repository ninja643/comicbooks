
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class HeroEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Version
	private int version;

	@Column
	private String name;

	@ManyToMany(mappedBy = "heroes")
	private List<ComicBookEntity> comicBooks;
}
