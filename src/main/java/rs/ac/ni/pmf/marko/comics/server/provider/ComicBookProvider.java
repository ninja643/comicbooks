package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface ComicBookProvider
{
	public Iterable<ComicBookEntity> getAll();

	public ComicBookEntity get(Long id) throws ResourceNotFoundException;
	
	public ComicBookEntity add(ComicBookEntity comicBook);
	
	public ComicBookEntity update(Long id, ComicBookEntity comicBook);
	
	public void deleteComicBook(Long id) throws ResourceNotFoundException;
	
}
