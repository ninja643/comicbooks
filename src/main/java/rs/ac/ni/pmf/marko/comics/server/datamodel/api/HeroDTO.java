package rs.ac.ni.pmf.marko.comics.server.datamodel.api;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class HeroDTO
{
	private Long id;

	private String name;
}
