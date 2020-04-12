package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

@RestController
@Api
@RequestMapping("/comicbook")
public class ComicBooksRestService
{
	@Autowired
	private ComicBookProvider comicBookProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<ComicBookDTO> getAll()
	{
		return comicBookProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookDTO getById(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException
	{
		return comicBookProvider.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(required = true) @RequestBody final ComicBookDTO comicBook)
			throws DuplicateResourceException
	{
		return comicBookProvider.add(comicBook);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(
			@PathVariable(name = "id", required = true) @NonNull final Long id,
			@RequestBody final ComicBookDTO comicBook) throws ResourceNotFoundException, BadRequestException
	{
		if (comicBook.getId() != null && comicBook.getId() != id)
		{
			throw new BadRequestException(ResourceType.COMIC_BOOK, "Comic book id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return comicBookProvider.update(id, comicBook);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException
	{
		comicBookProvider.deleteComicBook(id);
	}
}
