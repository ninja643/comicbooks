package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import rs.ac.ni.pmf.marko.comics.server.datamodel.PublisherEntity;
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
	public Iterable<PublisherEntity> getAll()
	{
		return _publisherProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PublisherEntity getById(@RequestParam(name = "id") long id) throws ResourceNotFoundException
	{
		return _publisherProvider.get(id);
	}

	@RequestMapping(
		value = "",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PublisherEntity add(@ApiParam(value = "Publisher to add", required = true) @RequestBody PublisherEntity publisher)
		throws DuplicateResourceException
	{
		return _publisherProvider.add(publisher);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestParam(name = "id") long id) throws ResourceNotFoundException
	{
		_publisherProvider.delete(id);
	}
}
