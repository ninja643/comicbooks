package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;

import java.util.List;

@Api
@RequestMapping("/publishers")
public interface PublishersRestController
{
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PublisherDTO> getAll();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PublisherDTO getById(@PathVariable(name = "id") final long id) throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(value = "Publisher to add", required = true) @RequestBody final PublisherDTO publisher) throws DuplicateResourceException;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final long id) throws ResourceNotFoundException;

	@RequestMapping(value = "/update/{id}",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(@PathVariable(name = "id") final Long id,
					   @RequestBody final PublisherDTO publisher) throws ResourceNotFoundException, BadRequestException;
}
