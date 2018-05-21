package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;

public interface ComicBookProvider
{
	public Iterable<ComicBook> getAllComicBooks();

	public ComicBook addComicBook(ComicBook comicBook);
}
