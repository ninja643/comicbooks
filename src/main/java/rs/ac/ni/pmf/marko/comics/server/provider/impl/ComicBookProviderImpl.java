package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.ComicBookConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

@Component
public class ComicBookProviderImpl implements ComicBookProvider
{
	@Autowired
	private ComicBooksRepository _comicBooksRepository;

	@Autowired
	private ComicBookConverter _comicBookConverter;

	@Override
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
	public Long update(final ComicBookDTO dto) throws ResourceNotFoundException
	{
		final ComicBookEntity entityToUpdate = _comicBooksRepository.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + dto.getId() + " does not exist"));

		entityToUpdate.setNumber(dto.getNumber());
		entityToUpdate.setFrontPageUrl(dto.getFrontPageUrl());
		entityToUpdate.setTitle(dto.getTitle());

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
