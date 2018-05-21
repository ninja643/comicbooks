package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
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
	public Optional<Publisher> get(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher add(Publisher publisher) throws DuplicateResourceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher update(int id, Publisher publisher) throws ResourceNotFoundException, DuplicateResourceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) throws ResourceNotFoundException
	{
		// TODO Auto-generated method stub

	}

}
