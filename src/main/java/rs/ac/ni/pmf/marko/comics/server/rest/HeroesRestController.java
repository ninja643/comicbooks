package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;

import java.util.List;

@Api
@RequestMapping(value = "/heroes")
public interface HeroesRestController
{
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<HeroDTO> getAll();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public HeroDTO getById(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@RequestBody final HeroDTO hero) throws DuplicateResourceException;

	@RequestMapping(value = "/update/{id}",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(@PathVariable(name = "id") final Long id, @RequestBody final HeroDTO hero)
			throws ResourceNotFoundException, BadRequestException;

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final Long id) throws ResourceNotFoundException;
}
