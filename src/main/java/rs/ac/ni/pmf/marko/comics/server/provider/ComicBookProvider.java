package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface ComicBookProvider
{
	public Iterable<ComicBookDTO> getAll();

	public ComicBookDTO get(Long id) throws ResourceNotFoundException;

	public ComicBookEntity add(ComicBookEntity comicBook) throws DuplicateResourceException;
	
	public ComicBookEntity update(Long id, ComicBookEntity comicBook) throws ResourceNotFoundException;
	
	public void deleteComicBook(Long id) throws ResourceNotFoundException;
	
}
