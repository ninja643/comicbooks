package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.HeroConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.repository.HeroRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeroProviderImpl implements HeroProvider
{
	@Autowired
	HeroRepository _heroRepository;

	@Override
	public Iterable<HeroDTO> getAll()
	{
		final List<HeroEntity> entities = _heroRepository.findAll();
		return entities.stream().map(e -> HeroConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public HeroDTO get(final Long id) throws ResourceNotFoundException
	{
		final HeroEntity entity = _heroRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ResourceType.HERO, "Hero with id: " + id + "doesn't exist"));
		return HeroConverter.dtoFromEntity(entity);
	}

	@Override
	public Long add(final HeroDTO hero) throws DuplicateResourceException
	{
		final HeroEntity heroEntity = HeroConverter.entityFromDto(hero);

		try
		{
			final HeroEntity savedEntity = _heroRepository.save(heroEntity);
			return savedEntity.getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.HERO,
					"Hero with id: " + heroEntity.getId() + " already exists");
		}
	}

	@Override
	public Long update(final Long id, final HeroDTO dto) throws ResourceNotFoundException
	{
		final HeroEntity entityToUpdate = _heroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.HERO, "Hero with id " + id + " does not exist"));

		entityToUpdate.setName(dto.getName());

		return _heroRepository.save(entityToUpdate).getId();
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		if (!_heroRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.HERO, "Hero with id " + id + " does not exist");
		}

		_heroRepository.deleteById(id);
	}
}
