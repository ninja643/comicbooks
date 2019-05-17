package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

import java.util.List;

public interface ComicBookProvider
{
	public List<ComicBookDTO> getAll();

	public ComicBookDTO get(Long id) throws ResourceNotFoundException;

	public Long add(ComicBookDTO comicBook) throws DuplicateResourceException;

	public Long update(ComicBookDTO comicBook) throws ResourceNotFoundException;

	public void deleteComicBook(Long id) throws ResourceNotFoundException;

}
