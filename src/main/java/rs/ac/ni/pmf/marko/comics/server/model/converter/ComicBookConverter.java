package rs.ac.ni.pmf.marko.comics.server.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublishersSeriesEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ComicBookConverter
{
	private final HeroConverter _heroConverter;

	public ComicBookDTO dtoFromEntity(final ComicBookEntity comicBookEntity)
	{
		final PublishersSeriesEntity publisherSeries = comicBookEntity.getPublisherSeries();

		final Set<HeroDTO> heroes = comicBookEntity.getHeroes().stream().map(_heroConverter::dtoFromEntity).collect(Collectors.toSet());

		return ComicBookDTO.builder()
				.id(comicBookEntity.getId())
				.number(comicBookEntity.getNumber())
				.title(comicBookEntity.getTitle())
				.frontPageUrl(comicBookEntity.getFrontPageUrl())
				.paperback(comicBookEntity.isPaperback())
				.paperSize(comicBookEntity.getPaperSize())
				.seriesId(publisherSeries.getId())
				.seriesName(publisherSeries.getSeries())
				.publisher(publisherSeries.getPublisher().getName())
				.heroes(heroes)
				.build();
	}

	public ComicBookEntity entityFromDto(final ComicBookDTO dto, final PublishersSeriesEntity publisherSeries, final List<HeroEntity> heroes)
	{
		return ComicBookEntity.builder()
				.id(dto.getId())
				.number(dto.getNumber())
				.title(dto.getTitle())
				.frontPageUrl(dto.getFrontPageUrl())
				.paperback(dto.isPaperback())
				.paperSize(dto.getPaperSize())
				.publisherSeries(publisherSeries)
				.heroes(heroes)
				.build();
	}
}
