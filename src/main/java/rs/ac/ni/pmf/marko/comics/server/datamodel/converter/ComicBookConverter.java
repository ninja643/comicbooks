package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

@Component
public class ComicBookConverter
{
	public ComicBookDTO dtoFromEntity(final ComicBookEntity comicBookEntity)
	{
		return new ComicBookDTO(comicBookEntity.getId(), comicBookEntity.getNumber(), comicBookEntity.getTitle(),
				comicBookEntity.getFrontPageUrl());
	}
}
