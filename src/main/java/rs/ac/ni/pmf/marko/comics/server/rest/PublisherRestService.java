package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

import java.util.List;

@RestController
@Api
@RequestMapping("/publisher")
public class PublisherRestService
{
	@Autowired
	private PublisherProvider _publisherProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PublisherDTO> getAll()
	{
		return _publisherProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PublisherDTO getById(@PathVariable(name = "id") final long id) throws ResourceNotFoundException
	{
		return _publisherProvider.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(value = "Publisher to add", required = true) @RequestBody final PublisherDTO publisher) throws DuplicateResourceException
	{
		return _publisherProvider.add(publisher);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final long id) throws ResourceNotFoundException
	{
		_publisherProvider.delete(id);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(@PathVariable(name = "id") final Long id, @RequestBody final PublisherDTO publisher) throws ResourceNotFoundException, BadRequestException
	{
		if (publisher.getId() != null && publisher.getId() != id)
		{
			throw new BadRequestException(ResourceType.PUBLISHER, "Publisher id cannot be changed. " +
					"You should not supply it in the request body");
		}
		return _publisherProvider.update(id, publisher);
	}
}
