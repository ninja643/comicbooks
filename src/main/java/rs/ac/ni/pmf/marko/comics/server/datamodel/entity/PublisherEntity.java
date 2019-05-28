package rs.ac.ni.pmf.marko.comics.server.datamodel.entity;

import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
@Table
public class PublisherEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

	@Column(name = "name", length = 200, nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	private Set<ComicBookEntity> comicBooks;
}
