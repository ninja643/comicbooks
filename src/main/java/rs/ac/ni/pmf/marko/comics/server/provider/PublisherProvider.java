package rs.ac.ni.pmf.marko.comics.server.provider;

import java.util.List;
import java.util.Optional;
import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public interface PublisherProvider
{
	List<Publisher> getAll();

	Optional<Publisher> get(int id);

	Publisher add(Publisher publisher) throws DuplicateResourceException;

	Publisher update(int id, Publisher publisher) throws ResourceNotFoundException, DuplicateResourceException;

	void delete(int id) throws ResourceNotFoundException;
}
