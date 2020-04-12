package rs.ac.ni.pmf.marko.comics.server.model.converter;

import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;

public class PublisherConverter
{
	public static PublisherDTO dtoFromEntity(final PublisherEntity entity)
	{
		return PublisherDTO.builder().id(entity.getId()).name(entity.getName()).build();
	}

	public static PublisherEntity entityFromDto(final PublisherDTO dto)
	{
		return PublisherEntity.builder().id(dto.getId()).name(dto.getName()).build();
	}
}
