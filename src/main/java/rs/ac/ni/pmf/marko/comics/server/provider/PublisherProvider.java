package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface PublisherProvider
{
	public Iterable<PublisherEntity> getAll();

	public PublisherEntity get(final Long id) throws ResourceNotFoundException;

	public PublisherEntity add(PublisherEntity publisher) throws DuplicateResourceException;

	public PublisherEntity update(final Long id, PublisherEntity publisher)
			throws ResourceNotFoundException, DuplicateResourceException;

	public void delete(final Long id) throws ResourceNotFoundException;
}
