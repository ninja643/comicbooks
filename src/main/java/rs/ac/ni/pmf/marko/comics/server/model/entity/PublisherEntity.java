package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "publishers", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
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
	private Set<PublishersSeriesEntity> series;
}
