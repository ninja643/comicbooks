package rs.ac.ni.pmf.marko.comics.server.datamodel.api;

import lombok.*;

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
}
