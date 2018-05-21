package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;
import rs.ac.ni.pmf.marko.comics.server.jpa.ComicBooksRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

public class DatabaseComicBookProvider implements ComicBookProvider
{
	@Autowired
	private ComicBooksRepository _comicBooksRepository;

	@Override
	public List<ComicBook> getAllComicBooks()
	{
		return _comicBooksRepository.findAll();
	}

	@Override
	public ComicBook addComicBook(ComicBook comicBook)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
