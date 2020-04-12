package rs.ac.ni.pmf.marko.comics.server.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;
import rs.ac.ni.pmf.marko.comics.server.rest.HeroesRestController;

@RestController
@RequiredArgsConstructor
public class HeroesRestControllerImpl implements HeroesRestController
{
	private final HeroProvider heroesService;

	@Override
	public Iterable<HeroDTO> getAll()
	{
		return heroesService.getAll();
	}

	@Override
	public HeroDTO getById(final Long id) throws ResourceNotFoundException
	{
		return heroesService.get(id);
	}

	@Override
	public Long add(final HeroDTO hero) throws DuplicateResourceException
	{
		return heroesService.add(hero);
	}

	@Override
	public Long update(final Long id, final HeroDTO hero) throws ResourceNotFoundException, BadRequestException
	{
		if (hero.getId() != null && hero.getId() != id)
		{
			throw new BadRequestException(ResourceType.HERO, "Hero id cannot be changed. " +
					"You should not supply it in the request body");
		}

		return heroesService.update(id, hero);
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		heroesService.delete(id);
	}
}
