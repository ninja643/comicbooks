package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.PublisherRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

public class PublisherProviderImpl implements PublisherProvider
{
	@Autowired
	private PublisherRepository _publisherRepository;

	@Override
	public Iterable<Publisher> getAll()
	{
		return _publisherRepository.findAll();
	}

	@Override
	public Publisher get(long id) throws ResourceNotFoundException
	{
		return _publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER, ""));
	}

	@Override
	public Publisher add(Publisher publisher) throws DuplicateResourceException
	{
		return _publisherRepository.save(publisher);
	}

	@Override
	public Publisher update(long id, Publisher publisher) throws ResourceNotFoundException, DuplicateResourceException
	{
		throwIfUnknownId(id);

		publisher.setId(id);

		return _publisherRepository.save(publisher);
	}

	@Override
	public void delete(long id) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);

		_publisherRepository.deleteById(id);
	}

	private void throwIfUnknownId(long id) throws ResourceNotFoundException
	{
		if (!_publisherRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist");
		}
	}
}
