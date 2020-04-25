package rs.ac.ni.pmf.marko.comics.server.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.rest.HeroesRestController;
import rs.ac.ni.pmf.marko.comics.server.service.HeroesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeroesRestControllerImpl implements HeroesRestController
{
	private final HeroesService _heroesService;

	@Override
	public List<HeroDTO> getAll()
	{
		return _heroesService.getAll();
	}

	@Override
	public HeroDTO getById(final Long id) throws ResourceNotFoundException
	{
		return _heroesService.get(id);
	}

	@Override
	public Long add(final HeroDTO hero) throws DuplicateResourceException
	{
		return _heroesService.add(hero);
	}

	@Override
	public Long update(final Long id, final HeroDTO hero) throws ResourceNotFoundException, BadRequestException
	{
		if (hero.getId() != null && hero.getId() != id)
		{
			throw new BadRequestException(ResourceType.HERO, "Hero id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return _heroesService.update(id, hero);
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		_heroesService.delete(id);
	}
}
