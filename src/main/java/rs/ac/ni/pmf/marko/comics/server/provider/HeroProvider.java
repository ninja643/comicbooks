package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.exception.*;

public interface HeroProvider
{
	public Iterable<HeroDTO> getAll();

	public HeroDTO get(Long id) throws ResourceNotFoundException;

	public Long add(HeroDTO hero) throws DuplicateResourceException;

	public Long update(Long id, HeroDTO hero) throws ResourceNotFoundException;

	public void delete(Long id) throws ResourceNotFoundException;

}
