package rs.ac.ni.pmf.marko.comics.server.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.PublisherConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.jpa.PublisherRepository;
import rs.ac.ni.pmf.marko.comics.server.provider.PublisherProvider;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherProviderImpl implements PublisherProvider
{
	@Autowired
	private PublisherRepository _publisherRepository;

	@Override
	public List<PublisherDTO> getAll()
	{
		final List<PublisherEntity> entities = _publisherRepository.findAll();
		return entities.stream().map(e -> PublisherConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	@Override
	public PublisherDTO get(final Long id) throws ResourceNotFoundException
	{
		final PublisherEntity publisherEntity = _publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER,
						"Publisher with id: " + id + " doesn't exist"));

		return PublisherConverter.dtoFromEntity(publisherEntity);
	}

	@Override
	public Long add(final PublisherDTO publisher) throws DuplicateResourceException
	{
		final PublisherEntity entity = PublisherConverter.entityFromDto(publisher);

		try
		{
			return _publisherRepository.save(entity).getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.PUBLISHER,
					"Publisger '" + publisher.getName() + "' already exists");
		}
	}

	@Override
	public Long update(final Long id, final PublisherDTO publisher) throws ResourceNotFoundException
	{
		final PublisherEntity entityToUpdate = _publisherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist"));

		entityToUpdate.setName(publisher.getName());

		return _publisherRepository.save(entityToUpdate).getId();
	}

	@Override
	public void delete(final Long id) throws ResourceNotFoundException
	{
		if (!_publisherRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist");
		}

		_publisherRepository.deleteById(id);
	}

}
