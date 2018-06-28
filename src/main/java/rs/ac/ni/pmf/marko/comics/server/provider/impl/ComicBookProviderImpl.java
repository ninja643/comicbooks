package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.ComicBookConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

public class ComicBookProviderImpl implements ComicBookProvider
{
	@Autowired
	private ComicBooksRepository _comicBooksRepository;

	@Autowired
	private ComicBookConverter _comicBookConverter;
	
	@Override
	public Iterable<ComicBookDTO> getAll()
	{
		List<ComicBookEntity> entities = _comicBooksRepository.findAll();
		return entities.stream().map(e -> _comicBookConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public ComicBookEntity add(final ComicBookEntity comicBook) throws DuplicateResourceException
	{
		try {
		return _comicBooksRepository.save(comicBook);
		}catch(final DataIntegrityViolationException e) {
			throw new DuplicateResourceException(ResourceType.COMIC_BOOK,
					"Comicbook with id: "+comicBook.getId() + "already exists");
		}
	}

	@Override
	public ComicBookDTO get(Long id) throws ResourceNotFoundException {
		
		ComicBookEntity entity = _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, ""));
		
		return _comicBookConverter.dtoFromEntity(entity);
	}

	@Override
	public ComicBookEntity update(Long id, ComicBookEntity comicBook) throws ResourceNotFoundException {
		
		throwIfUnknownId(id);
		
		comicBook.setId(id);
		
		return _comicBooksRepository.save(comicBook);
		
	}

	@Override
	public void deleteComicBook(Long id) throws ResourceNotFoundException{
		
		throwIfUnknownId(id);
		
		_comicBooksRepository.deleteById(id);
		
	}
	
	private void throwIfUnknownId(final long id) throws ResourceNotFoundException
	{
		if (!_comicBooksRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.COMIC_BOOK, "Comic book with id " + id + " does not exist");
		}
	}

}
