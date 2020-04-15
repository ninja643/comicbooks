package rs.ac.ni.pmf.marko.comics.server.model.converter;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;

@Component
public class PublisherConverter
{
	public PublisherDTO dtoFromEntity(final PublisherEntity entity)
	{
		return PublisherDTO.builder().id(entity.getId()).name(entity.getName()).build();
	}

	public PublisherEntity entityFromDto(final PublisherDTO dto)
	{
		return PublisherEntity.builder().id(dto.getId()).name(dto.getName()).version(0).build();
	}
}
