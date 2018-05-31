package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.jpa.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

public class ComicBookProviderImpl implements ComicBookProvider
{
	@Autowired
	private ComicBooksRepository _comicBooksRepository;

	@Override
	public List<ComicBookEntity> getAllComicBooks()
	{
		return _comicBooksRepository.findAll();
	}

	@Override
	public ComicBookEntity addComicBook(ComicBookEntity comicBook)
	{
		return _comicBooksRepository.save(comicBook);
	}

}
