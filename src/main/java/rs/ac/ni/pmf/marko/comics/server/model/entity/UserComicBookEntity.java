package rs.ac.ni.pmf.marko.comics.server.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users_comicbooks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserComicBookEntity
{
	@EmbeddedId
	private UserComicBookId id;

	@Column
	private int quantity;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@MapsId("comicbook_id")
	@JoinColumn(name = "comicbook_id")
	private ComicBookEntity comicBook;

	@Embeddable
	@Data
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class UserComicBookId implements Serializable
	{
		@Column(name = "user_id")
		private Long userId;

		@Column(name = "comicbook_id")
		private Long comicBookId;
	}
}
