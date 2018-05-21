package rs.ac.ni.pmf.marko.comics.server.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;

public interface ComicBookProvider
{
	public List<ComicBook> getAllComicBooks();

	public ComicBook addComicBook(ComicBook comicBook);
}
