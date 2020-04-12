package rs.ac.ni.pmf.marko.comics.server.model.converter;

import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;

public class HeroConverter
{
	public static HeroDTO dtoFromEntity(final HeroEntity heroEntity)
	{
		return new HeroDTO(heroEntity.getId(), heroEntity.getName());
	}

	public static HeroEntity entityFromDto(final HeroDTO dto)
	{
		return HeroEntity.builder().id(dto.getId()).name(dto.getName()).build();
	}
}
