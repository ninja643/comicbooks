package rs.ac.ni.pmf.marko.comics.server.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.ComicBookConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.repository.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.repository.HeroesRepository;
import rs.ac.ni.pmf.marko.comics.server.repository.PublishersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComicBooksService
{
	private final ComicBooksRepository _comicBooksRepository;
	private final HeroesRepository _heroesRepository;
	private final PublishersRepository _publishersRepository;

	@Transactional
	public List<ComicBookDTO> getAll()
	{
		final List<ComicBookEntity> entities = _comicBooksRepository.findAll();
		return entities.stream().map(e -> ComicBookConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	public ComicBookDTO get(final Long id) throws ResourceNotFoundException
	{
		final ComicBookEntity entity = _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, ""));

		return ComicBookConverter.dtoFromEntity(entity);
	}

	public Long add(final ComicBookDTO comicBookDTO) throws DuplicateResourceException
	{
		try
		{
			final ComicBookEntity comicBookEntity = ComicBookConverter.entityFromDto(comicBookDTO);
			final ComicBookEntity savedEntity = _comicBooksRepository.save(comicBookEntity);

			return savedEntity.getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.COMIC_BOOK,
					"Comic book with id: " + comicBookDTO.getId() + "already exists");
		}
	}

	public Long update(final Long id, final ComicBookDTO dto) throws ResourceNotFoundException
	{
		final ComicBookEntity entityToUpdate = _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + dto.getId() + " does not exist"));

		entityToUpdate.setNumber(dto.getNumber());
		entityToUpdate.setFrontPageUrl(dto.getFrontPageUrl());
		entityToUpdate.setTitle(dto.getTitle());

		final PublisherDTO publisher = dto.getPublisher();
		if (publisher != null)
		{
			final PublisherEntity publisherEntity = _publishersRepository.findById(publisher.getId())
					.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Unknown publisher " + publisher.getName()));

			entityToUpdate.setPublisher(publisherEntity);
		}

		final Set<HeroDTO> heroes = dto.getHeroes();
		if (heroes != null && !heroes.isEmpty())
		{
			final List<HeroEntity> heroEntities = new ArrayList<>();

			for (final HeroDTO hero : heroes)
			{
				final HeroEntity heroEntity = _heroesRepository.findById(hero.getId())
						.orElseThrow(() -> new ResourceNotFoundException(ResourceType.HERO, "Unknown hero " + hero.getName()));
				heroEntities.add(heroEntity);
			}

			entityToUpdate.setHeroes(heroEntities);
		}

		return _comicBooksRepository.save(entityToUpdate).getId();

	}

	public void deleteComicBook(final Long id) throws ResourceNotFoundException
	{
		if (!_comicBooksRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + id + " does not exist");
		}

		_comicBooksRepository.deleteById(id);
	}
}