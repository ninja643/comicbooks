package rs.ac.ni.pmf.marko.comics.server.model.entity;

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

import lombok.*;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"number", "publisher_id"})})
@Builder
public class ComicBookEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

	@Column
	private Integer number;

	@Column
	private String title;

	@Column
	private String frontPageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	private PublisherEntity publisher;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<HeroEntity> heroes;
}
