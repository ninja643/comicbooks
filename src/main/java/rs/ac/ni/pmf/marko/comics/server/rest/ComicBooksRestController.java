package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;

@Api(tags = "Rest operations on comic books")
@RequestMapping("/comicbooks")
public interface ComicBooksRestController
{
	@ApiOperation(value = "Get all comic books")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<ComicBookDTO> getAll();

	@ApiOperation(value = "Get a comic book by ID", response = ComicBookDTO.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookDTO getById(@ApiParam(required = true, value = "ID of the requested comic book", example = "0") @PathVariable(name = "id") final Long id) throws ResourceNotFoundException;

	@ApiOperation("Add a comic book to the collection")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(required = true, value = "Comic book") @RequestBody final ComicBookDTO comicBook) throws DuplicateResourceException;

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(
			@PathVariable(name = "id", required = true) @NonNull final Long id,
			@RequestBody final ComicBookDTO comicBook) throws ResourceNotFoundException, BadRequestException;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException;
}
