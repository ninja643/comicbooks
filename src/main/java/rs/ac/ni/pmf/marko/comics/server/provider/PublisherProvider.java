package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface PublisherProvider
{
	Iterable<PublisherEntity> getAll();

	PublisherEntity get(long id) throws ResourceNotFoundException;

	PublisherEntity add(PublisherEntity publisher) throws DuplicateResourceException;

	PublisherEntity update(long id, PublisherEntity publisher) throws ResourceNotFoundException, DuplicateResourceException;

	void delete(long id) throws ResourceNotFoundException;
}
