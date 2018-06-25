package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;

@RestController
@Api
@RequestMapping(value="/hero")
public class HeroRestService {

	@Autowired
	HeroProvider dataProvider;
	
	@RequestMapping(value = "",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<HeroEntity> getAll()
	{
		return dataProvider.getAll();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeroEntity getById(@RequestParam(name = "id") Long id) throws ResourceNotFoundException 
	{
		return dataProvider.get(id);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeroEntity add(@RequestBody HeroEntity heroEntity) throws DuplicateResourceException
	{
		return dataProvider.add(heroEntity);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeroEntity update(@RequestParam(name = "id") Long id, @RequestBody HeroEntity heroEntity)
	{
		return dataProvider.update(id, heroEntity);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestParam(name = "id")Long id) throws ResourceNotFoundException
	{
			dataProvider.delete(id);
	}
	
	
}













