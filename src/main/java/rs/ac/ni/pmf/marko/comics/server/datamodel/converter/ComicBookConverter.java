package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

public class ComicBookConverter
{
	public static ComicBookDTO dtoFromEntity(final ComicBookEntity comicBookEntity)
	{
		return ComicBookDTO.builder()
				.id(comicBookEntity.getId())
				.number(comicBookEntity.getNumber())
				.title(comicBookEntity.getTitle())
				.frontPageUrl(comicBookEntity.getFrontPageUrl())
				.build();
	}

	public static ComicBookEntity entityFromDto(final ComicBookDTO dto)
	{
		return ComicBookEntity.builder()
				.id(dto.getId())
				.number(dto.getNumber())
				.title(dto.getTitle())
				.frontPageUrl(dto.getFrontPageUrl())
				.build();
	}
}
