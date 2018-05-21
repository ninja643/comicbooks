package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

@RestController
@RequestMapping("/publisher")
public class PublisherRestService
{
	@Autowired
	private PublisherProvider _publisherProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<Publisher> getAll()
	{
		return _publisherProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Publisher getById(@RequestParam(name = "id") long id) throws ResourceNotFoundException
	{
		return _publisherProvider.get(id);
	}

	@RequestMapping(
		value = "",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Publisher add(@RequestBody Publisher publisher) throws DuplicateResourceException
	{
		return _publisherProvider.add(publisher);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestParam(name = "id") long id) throws ResourceNotFoundException
	{
		_publisherProvider.delete(id);
	}
}
