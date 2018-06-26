package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

public interface ComicBookProvider
{
	public Iterable<ComicBookEntity> getAllComicBooks();

	public ComicBookEntity addComicBook(ComicBookEntity comicBook);
}
