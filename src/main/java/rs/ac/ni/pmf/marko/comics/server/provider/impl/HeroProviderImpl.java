package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.HeroConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.HeroRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;

@Component
public class HeroProviderImpl implements HeroProvider
{
	@Autowired
	HeroRepository _heroRepository;
	
	@Autowired
	HeroConverter _heroConverter;
	
	@Override
	public Iterable<HeroDTO> getAll() 
	{
		List<HeroEntity> entities = _heroRepository.findAll();
		return entities.stream().map(e -> _heroConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public HeroDTO get(Long id) throws ResourceNotFoundException 
	{
		HeroEntity entity = _heroRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.HERO, 
						"Hero with id: "+id+"doesn't exist"));
		return _heroConverter.dtoFromEntity(entity);

	}

	@Override
	public HeroEntity add(HeroEntity heroEntity) throws DuplicateResourceException
	{
		try {
			return _heroRepository.save(heroEntity);
		}catch(final DataIntegrityViolationException e) {
			throw new DuplicateResourceException(ResourceType.HERO,
					"Hero with id: "+heroEntity.getId()+" already exists");
		}
	}

	@Override
	public HeroEntity update(Long id, HeroEntity heroEntity) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);
		
		heroEntity.setId(id);
		
		return _heroRepository.save(heroEntity);
	}

	@Override
	public void delete(Long id) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);
		
		_heroRepository.deleteById(id);

	}

	private void throwIfUnknownId(final Long id) throws ResourceNotFoundException
	{
		if (!_heroRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.HERO, "Hero with id " + id + " does not exist");
		}
	}
	
}
