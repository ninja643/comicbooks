package rs.ac.ni.pmf.marko.comics.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;
import rs.ac.ni.pmf.marko.comics.server.provider.DataProvider;

@RestController
@RequestMapping("/comicbook")
public class ComicBooksRestService
{
	@Autowired
	private DataProvider dataProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ComicBook> getAllComicBooks()
	{
		return dataProvider.getAllComicBooks();
	}
	
	@RequestMapping(value = "/single", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ComicBook getSingleComicBook()
	{
		return dataProvider.getAllComicBooks().get(0);
	}	
}
