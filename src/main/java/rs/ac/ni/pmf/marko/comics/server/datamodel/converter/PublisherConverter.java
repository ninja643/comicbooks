package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;

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
