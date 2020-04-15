package rs.ac.ni.pmf.marko.comics.server.model.converter;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherSeriesDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublishersSeriesEntity;

@Component
public class PublisherSeriesConverter
{
	public PublisherSeriesDTO toDto(final PublishersSeriesEntity entity)
	{
		return PublisherSeriesDTO.builder()
				.id(entity.getId())
				.defaultSeries(entity.isDefaultSeries())
				.series(entity.getSeries())
				.build();
	}

	public PublishersSeriesEntity toEntity(final PublisherSeriesDTO dto, final PublisherEntity publisherEntity)
	{
		return PublishersSeriesEntity.builder()
				.id(dto.getId())
				.defaultSeries(dto.isDefaultSeries())
				.series(dto.getSeries())
				.publisher(publisherEntity)
				.build();
	}
}
