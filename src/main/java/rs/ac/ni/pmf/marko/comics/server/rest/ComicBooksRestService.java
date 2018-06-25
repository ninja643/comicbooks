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
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

@RestController
@Api
@RequestMapping("/comicbook")
public class ComicBooksRestService
{
	@Autowired
	private ComicBookProvider dataProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<ComicBookEntity> getAll()
	{
		return dataProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookEntity getById(@RequestParam(name = "id") Long id) throws ResourceNotFoundException
	{
			return dataProvider.get(id);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookEntity add(@ApiParam(required = true) @RequestBody final ComicBookEntity comicBook) throws DuplicateResourceException
	{
		return dataProvider.add(comicBook);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBookEntity update(@RequestParam(name = "id")Long id, @RequestBody ComicBookEntity comicBookEntity) throws ResourceNotFoundException 
	{
		return dataProvider.update(id, comicBookEntity);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestParam(name = "id")Long id) throws ResourceNotFoundException 
	{
		dataProvider.deleteComicBook(id);
	}
}
