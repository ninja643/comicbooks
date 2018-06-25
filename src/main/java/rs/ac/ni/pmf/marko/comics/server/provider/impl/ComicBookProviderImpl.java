package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

public class ComicBookProviderImpl implements ComicBookProvider
{
	@Autowired
	private ComicBooksRepository _comicBooksRepository;

	@Override
	public List<ComicBookEntity> getAll()
	{
		return _comicBooksRepository.findAll();
	}

	@Override
	public ComicBookEntity add(final ComicBookEntity comicBook)
	{
		return _comicBooksRepository.save(comicBook);
	}

	@Override
	public ComicBookEntity get(Long id) throws ResourceNotFoundException {
		
		return _comicBooksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.COMIC_BOOK, ""));
		
	}

	@Override
	public ComicBookEntity update(Long id, ComicBookEntity comicBook) {
		
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
