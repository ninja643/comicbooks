package rs.ac.ni.pmf.marko.comics.server.provider;

import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface PublisherProvider
{
	Iterable<Publisher> getAll();

	Publisher get(long id) throws ResourceNotFoundException;

	Publisher add(Publisher publisher) throws DuplicateResourceException;

	Publisher update(long id, Publisher publisher) throws ResourceNotFoundException, DuplicateResourceException;

	void delete(long id) throws ResourceNotFoundException;
}
