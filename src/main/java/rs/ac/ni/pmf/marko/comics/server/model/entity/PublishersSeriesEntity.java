package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "publishers_series")
@Builder
public class PublishersSeriesEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String series;

	@Column(name = "is_default")
	private boolean isDefault;

	@ManyToOne(fetch = FetchType.EAGER)
	private PublisherEntity publisher;
}
