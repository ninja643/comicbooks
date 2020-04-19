package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "heroes", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class HeroEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private int version;

	@Column
	private String name;

	@ManyToMany(mappedBy = "heroes")
	private List<ComicBookEntity> comicBooks;
}
