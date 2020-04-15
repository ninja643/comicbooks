package rs.ac.ni.pmf.marko.comics.server.model.converter;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;

@Component
public class HeroConverter
{
	public HeroDTO dtoFromEntity(final HeroEntity heroEntity)
	{
		return new HeroDTO(heroEntity.getId(), heroEntity.getName());
	}

	public HeroEntity entityFromDto(final HeroDTO dto)
	{
		return HeroEntity.builder().id(dto.getId()).name(dto.getName()).version(0).build();
	}
}
