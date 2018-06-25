package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import rs.ac.ni.pmf.marko.comics.server.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.HeroRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.HeroProvider;

public class HeroProviderImpl implements HeroProvider {

	@Autowired
	HeroRepository _heroRepository;
	
	@Override
	public Iterable<HeroEntity> getAll() 
	{
		return _heroRepository.findAll();
	}

	@Override
	public HeroEntity get(Long id) throws ResourceNotFoundException 
	{
		return _heroRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(ResourceType.HERO, 
						"Hero with id: "+id+"doesn't exist"));
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
	public HeroEntity update(Long id, HeroEntity heroEntity)
	{
		throwIfUnknownId(id);
		
		heroEntity.setId(id);
		
		_heroRepository.save(heroEntity);
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
