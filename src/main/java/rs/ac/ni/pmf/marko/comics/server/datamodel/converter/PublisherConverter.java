package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;

@Component
public class PublisherConverter 
{
	public PublisherDTO dtoFromEntity(PublisherEntity entity)
	{
		return new PublisherDTO(entity.getId(), entity.getName());
	}
}
