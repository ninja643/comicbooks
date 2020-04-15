package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;
import rs.ac.ni.pmf.marko.comics.server.model.PaperSize;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comicbooks", uniqueConstraints = {@UniqueConstraint(columnNames = {"number", "publisher_series_id"})})
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

	@Column(name = "front_page_url")
	private String frontPageUrl;

	@Builder.Default
	@Column(name = "is_paperback")
	private boolean paperback = true;

	@Builder.Default
	@Column(name = "paper_size")
	private PaperSize paperSize = PaperSize.B5;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "publisher_series_id")
	private PublishersSeriesEntity publisherSeries;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "comicbooks_heroes",
			   joinColumns = @JoinColumn(name = "comicbook_id"),
			   inverseJoinColumns = @JoinColumn(name = "hero_id"))
	private List<HeroEntity> heroes;

	@OneToMany(mappedBy = "comicBook")
	private List<UserComicBookEntity> users;
}
