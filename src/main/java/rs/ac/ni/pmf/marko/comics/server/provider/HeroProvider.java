package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface HeroProvider
{
	public Iterable<HeroDTO> getAll();

	public HeroDTO get(Long id) throws ResourceNotFoundException;

	public HeroEntity add(HeroEntity heroEntity) throws DuplicateResourceException;

	public HeroEntity update(Long id, HeroEntity heroEntity) throws ResourceNotFoundException;

	public void delete(Long id) throws ResourceNotFoundException;

}
