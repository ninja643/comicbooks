package rs.ac.ni.pmf.marko.comics.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.HeroConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.repository.HeroesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroesService
{
	private final HeroesRepository _heroesRepository;
	private final HeroConverter _heroConverter;

	public List<HeroDTO> getAll()
	{
		final List<HeroEntity> entities = _heroesRepository.findAll();
		return entities.stream().map(heroEntity -> _heroConverter.dtoFromEntity(heroEntity)).collect(Collectors.toList());
	}

	public HeroDTO get(final Long id) throws ResourceNotFoundException
	{
		final HeroEntity entity = _heroesRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.HERO, "Hero with id: " + id + "doesn't exist"));
		return _heroConverter.dtoFromEntity(entity);
	}

	public Long add(final HeroDTO hero) throws DuplicateResourceException
	{
		final HeroEntity heroEntity = _heroConverter.entityFromDto(hero);

		try
		{
			final HeroEntity savedEntity = _heroesRepository.save(heroEntity);
			return savedEntity.getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.HERO,
					"Hero with id: " + heroEntity.getId() + " already exists");
		}
	}

	public Long update(final Long id, final HeroDTO dto) throws ResourceNotFoundException
	{
		final HeroEntity entityToUpdate = _heroesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.HERO, "Hero with id " + id + " does not exist"));

		entityToUpdate.setName(dto.getName());

		return _heroesRepository.save(entityToUpdate).getId();
	}

	public void delete(final Long id) throws ResourceNotFoundException
	{
		if (!_heroesRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.HERO, "Hero with id " + id + " does not exist");
		}

		_heroesRepository.deleteById(id);
	}
}
