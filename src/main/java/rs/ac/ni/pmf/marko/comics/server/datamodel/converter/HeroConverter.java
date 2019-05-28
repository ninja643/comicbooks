package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.HeroEntity;

@Component
public class HeroConverter
{
	public HeroDTO dtoFromEntity(final HeroEntity heroEntity)
	{
		return new HeroDTO(heroEntity.getId(), heroEntity.getName());
	}

	public HeroEntity entityFromDto(final HeroDTO dto)
	{
		return HeroEntity.builder().id(dto.getId()).name(dto.getName()).build();
	}
}
