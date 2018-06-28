package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import rs.ac.ni.pmf.marko.comics.server.datamodel.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.converter.PublisherConverter;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.PublisherRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

public class PublisherProviderImpl implements PublisherProvider
{
	@Autowired
	private PublisherRepository _publisherRepository;
	
	@Autowired
	private PublisherConverter _publisherConverter;

	@Override
	public List<PublisherDTO> getAll()
	{
		final List<PublisherEntity> entities = _publisherRepository.findAll();
		
		return entities.stream().map(e -> _publisherConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public PublisherDTO get(final Long id) throws ResourceNotFoundException

	{
		PublisherEntity publisherEntity = _publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id: "+ id + " doesn't exist"));
		
		return _publisherConverter.dtoFromEntity(publisherEntity);
	}

	@Override
	public PublisherEntity add(final PublisherEntity publisher) throws DuplicateResourceException
	{
		try
		{
			return _publisherRepository.save(publisher);
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.PUBLISHER,
					"Publisger '" + publisher.getName() + "' already exists");
		}
	}

	@Override
	public PublisherEntity update(final Long id, final PublisherEntity publisher)
			throws ResourceNotFoundException, DuplicateResourceException
	{
		throwIfUnknownId(id);

		publisher.setId(id);

		return _publisherRepository.save(publisher);
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		throwIfUnknownId(id);

		_publisherRepository.deleteById(id);
	}

	private void throwIfUnknownId(final long id) throws ResourceNotFoundException
	{
		if (!_publisherRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist");
		}
	}
}
