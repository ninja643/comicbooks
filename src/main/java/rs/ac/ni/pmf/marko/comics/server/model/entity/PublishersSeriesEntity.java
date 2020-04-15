package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "publishers_series", uniqueConstraints = @UniqueConstraint(columnNames = {"series"}))
@Builder
public class PublishersSeriesEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String series;

	@Column(name = "is_default")
	private boolean defaultSeries;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private PublisherEntity publisher;
}
