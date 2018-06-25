package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface HeroProvider {

	public Iterable<HeroEntity> getAll();
	
	public HeroEntity get(Long id) throws ResourceNotFoundException;
	
	public HeroEntity add(HeroEntity heroEntity) throws DuplicateResourceException;
	
	public HeroEntity update(Long id, HeroEntity heroEntity) throws ResourceNotFoundException;
	
	public void delete(Long id) throws ResourceNotFoundException;
	
}
