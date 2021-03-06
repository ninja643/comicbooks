package rs.ac.ni.pmf.marko.comics.server.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.rest.ComicBooksRestController;
import rs.ac.ni.pmf.marko.comics.server.service.ComicBooksService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ComicBooksRestControllerImpl implements ComicBooksRestController
{
	private final ComicBooksService _comicBooksService;

	@Override
	public List<ComicBookDTO> getAll()
	{
		return _comicBooksService.getAll();
	}

	@Override
	public ComicBookDTO getById(final Long id) throws ResourceNotFoundException
	{
		return _comicBooksService.get(id);
	}

	@Override
	public Long add(final ComicBookDTO comicBook) throws DuplicateResourceException, ResourceNotFoundException
	{
		return _comicBooksService.add(comicBook);
	}

	@Override
	public Long update(final Long id, final ComicBookDTO comicBook) throws ResourceNotFoundException, BadRequestException
	{
		if (comicBook.getId() != null && comicBook.getId() != id)
		{
			throw new BadRequestException(ResourceType.COMIC_BOOK, "Comic book id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return _comicBooksService.update(id, comicBook);
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		_comicBooksService.deleteComicBook(id);
	}
}
