package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.ComicBookConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.*;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.jpa.*;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

@Component
@RequiredArgsConstructor
public class ComicBookProviderImpl implements ComicBookProvider
{
	private final ComicBooksRepository _comicBooksRepository;
	private final HeroRepository _heroRepository;
	private final PublisherRepository _publisherRepository;

	private final ComicBookConverter _comicBookConverter;

	@Override
	@Transactional
	public List<ComicBookDTO> getAll()
	{
		final List<ComicBookEntity> entities = _comicBooksRepository.findAll();
		return entities.stream().map(e -> _comicBookConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public Long add(final ComicBookDTO comicBookDTO) throws DuplicateResourceException
	{
		try
		{
			final ComicBookEntity comicBookEntity = _comicBookConverter.entityFromDto(comicBookDTO);
			final ComicBookEntity savedEntity = _comicBooksRepository.save(comicBookEntity);

			return savedEntity.getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.COMIC_BOOK,
					"Comicbook with id: " + comicBookDTO.getId() + "already exists");
		}
	}

	@Override
	public ComicBookDTO get(final Long id) throws ResourceNotFoundException
	{

		final ComicBookEntity entity = _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, ""));

		return _comicBookConverter.dtoFromEntity(entity);
	}

	@Override
	public Long update(final Long id, final ComicBookDTO dto) throws ResourceNotFoundException, BadRequestException
	{
		final ComicBookEntity entityToUpdate = _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + dto.getId() + " does not exist"));

		entityToUpdate.setNumber(dto.getNumber());
		entityToUpdate.setFrontPageUrl(dto.getFrontPageUrl());
		entityToUpdate.setTitle(dto.getTitle());

		PublisherDTO publisher = dto.getPublisher();
		if (publisher != null)
		{

			final PublisherEntity publisherEntity = _publisherRepository.findById(publisher.getId())
					.orElseThrow(() -> new BadRequestException(ResourceType.COMIC_BOOK, "Unknown publisher " + publisher.getName()));

			entityToUpdate.setPublisher(publisherEntity);
		}

		if (dto.getHeroes() != null)
		{
			dto.getHeroes().forEach(hero -> {
				final HeroEntity heroEntity = _heroRepository.findById()
			});
		}

		return _comicBooksRepository.save(entityToUpdate).getId();

	}

	@Override
	public void deleteComicBook(final Long id) throws ResourceNotFoundException
	{
		if (!_comicBooksRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + id + " does not exist");
		}

		_comicBooksRepository.deleteById(id);

	}
}
