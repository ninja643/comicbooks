package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface ComicBookProvider
{
	public Iterable<ComicBookEntity> getAll();

	public ComicBookEntity get(Long id) throws ResourceNotFoundException;
	
	public ComicBookEntity add(ComicBookEntity comicBook) throws DuplicateResourceException;
	
	public ComicBookEntity update(Long id, ComicBookEntity comicBook) throws ResourceNotFoundException;
	
	public void deleteComicBook(Long id) throws ResourceNotFoundException;
	
}
