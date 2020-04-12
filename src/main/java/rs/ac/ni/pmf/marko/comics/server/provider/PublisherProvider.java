package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

import java.util.List;

public interface PublisherProvider
{
	public List<PublisherDTO> getAll();

	public PublisherDTO get(final Long id) throws ResourceNotFoundException;

	public Long add(PublisherDTO publisher) throws DuplicateResourceException;

	public Long update(final Long id, PublisherDTO publisher) throws ResourceNotFoundException;

	public void delete(final Long id) throws ResourceNotFoundException;
}
