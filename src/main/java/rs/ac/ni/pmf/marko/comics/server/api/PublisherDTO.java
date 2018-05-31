package rs.ac.ni.pmf.marko.comics.server.api;

import lombok.Value;
import rs.ac.ni.pmf.marko.comics.server.datamodel.PublisherEntity;

@Value
public class PublisherDTO
{
	long id;
	String name;

	public static PublisherDTO fromEntiry(PublisherEntity publisherEntity)
	{
		return new PublisherDTO(publisherEntity.getId(), publisherEntity.getName());
	}

	public PublisherEntity toEntity()
	{
		return new PublisherEntity(getId(), 0, getName(), null);
	}
}
