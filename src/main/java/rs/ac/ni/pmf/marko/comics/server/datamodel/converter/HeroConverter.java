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
}
