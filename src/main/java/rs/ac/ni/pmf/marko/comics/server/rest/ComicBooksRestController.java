package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;

import java.util.List;

@Api(tags = "Rest operations on comic books")
@RequestMapping("/comicbooks")
public interface ComicBooksRestController
{
	@ApiOperation(value = "Get all comic books")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ComicBookDTO> getAll();

	@ApiOperation(value = "Get a comic book by ID", response = ComicBookDTO.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookDTO getById(@ApiParam(required = true,
										  value = "ID of the requested comic book",
										  example = "0") @PathVariable(name = "id") final Long id) throws ResourceNotFoundException;

	@ApiOperation("Add a comic book to the collection")
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(required = true,
							  value = "Comic book") @RequestBody final ComicBookDTO comicBook) throws DuplicateResourceException, ResourceNotFoundException;

	@RequestMapping(value = "/update/{id}",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(
			@PathVariable(name = "id", required = true) @NonNull final Long id,
			@RequestBody final ComicBookDTO comicBook) throws ResourceNotFoundException, BadRequestException;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException;
}
