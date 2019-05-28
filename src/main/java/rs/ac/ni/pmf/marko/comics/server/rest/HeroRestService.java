package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;

@RestController
@Api
@RequestMapping(value = "/hero")
public class HeroRestService
{

	@Autowired
	HeroProvider heroProvider;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<HeroDTO> getAll()
	{
		return heroProvider.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeroDTO getById(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException

	{
		return heroProvider.get(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@RequestBody final HeroDTO hero) throws DuplicateResourceException
	{
		return heroProvider.add(hero);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(@PathVariable(name = "id") final Long id, @RequestBody final HeroDTO hero)
			throws ResourceNotFoundException, BadRequestException
	{
		if (hero.getId() != null && hero.getId() != id)
		{
			throw new BadRequestException(ResourceType.HERO, "Hero id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return heroProvider.update(id, hero);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)

	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException
	{
		heroProvider.delete(id);
	}
}
