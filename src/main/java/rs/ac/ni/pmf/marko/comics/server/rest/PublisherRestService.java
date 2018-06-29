package rs.ac.ni.pmf.marko.comics.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

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
	public PublisherEntity add(
			@ApiParam(value = "Publisher to add", required = true) @RequestBody final PublisherEntity publisher)
			throws DuplicateResourceException
	{
		return _publisherProvider.add(publisher);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final long id) throws ResourceNotFoundException
	{
		_publisherProvider.delete(id);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE) 
	public PublisherEntity update(@PathVariable(name = "id") Long id, @RequestBody PublisherEntity publisherEntity) throws ResourceNotFoundException, DuplicateResourceException 
	{
		return _publisherProvider.update(id,publisherEntity);
	}
}










