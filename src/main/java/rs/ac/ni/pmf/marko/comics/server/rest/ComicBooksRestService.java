package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;
import rs.ac.ni.pmf.marko.comics.server.provider.ComicBookProvider;

@RestController
@Api
@RequestMapping("/comicbook")
public class ComicBooksRestService
{
	@Autowired
	private ComicBookProvider dataProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<ComicBook> getAllComicBooks()
	{
		return dataProvider.getAllComicBooks();
	}

	@RequestMapping(
		value = "",
		method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ComicBook addComicBook(@ApiParam(required = true) @RequestBody ComicBook comicBook)
	{
		return dataProvider.addComicBook(comicBook);
	}
}
