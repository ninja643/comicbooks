package rs.ac.ni.pmf.marko.comics.server.datamodel.api;

import lombok.*;

import java.util.Set;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ComicBookDTO
{
	private Long id;
	private int number;
	private String title;
	private String frontPageUrl;

	private PublisherDTO publisher;
	private Set<HeroDTO> heroes;
}
