package rs.ac.ni.pmf.marko.comics.server.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;
import rs.ac.ni.pmf.marko.comics.server.rest.ComicBooksRestController;

@RestController
@RequiredArgsConstructor
public class ComicBooksRestControllerImpl implements ComicBooksRestController
{
	private final ComicBookProvider comicBooksService;

	@Override
	public Iterable<ComicBookDTO> getAll()
	{
		return comicBooksService.getAll();
	}

	@Override
	public ComicBookDTO getById(final Long id) throws ResourceNotFoundException
	{
		return comicBooksService.get(id);
	}

	@Override
	public Long add(final ComicBookDTO comicBook) throws DuplicateResourceException
	{
		return comicBooksService.add(comicBook);
	}

	@Override
	public Long update(final Long id, final ComicBookDTO comicBook) throws ResourceNotFoundException, BadRequestException
	{
		if (comicBook.getId() != null && comicBook.getId() != id)
		{
			throw new BadRequestException(ResourceType.COMIC_BOOK, "Comic book id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return comicBooksService.update(id, comicBook);
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		comicBooksService.deleteComicBook(id);
	}
}
