package rs.ac.ni.pmf.marko.comics.server.model.api;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PublisherDTO
{
	private Long id;
	private String name;
}
