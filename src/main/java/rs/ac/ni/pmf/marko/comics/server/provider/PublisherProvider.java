package rs.ac.ni.pmf.marko.comics.server.provider;

import java.util.List;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface PublisherProvider
{
	public List<PublisherDTO> getAll();

	public PublisherDTO get(final Long id) throws ResourceNotFoundException;

	public PublisherEntity add(PublisherEntity publisher) throws DuplicateResourceException;

	public PublisherEntity update(final Long id, PublisherEntity publisher)
			throws ResourceNotFoundException, DuplicateResourceException;

	public void delete(final Long id) throws ResourceNotFoundException;
}
