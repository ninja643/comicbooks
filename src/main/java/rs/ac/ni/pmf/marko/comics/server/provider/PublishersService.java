package rs.ac.ni.pmf.marko.comics.server.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.PublisherConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.repository.PublishersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublishersService
{
	private final PublishersRepository _publishersRepository;

	public List<PublisherDTO> getAll()
	{
		final List<PublisherEntity> entities = _publishersRepository.findAll();
		return entities.stream().map(e -> PublisherConverter.dtoFromEntity(e)).collect(Collectors.toList());
	}

	public PublisherDTO get(final Long id) throws ResourceNotFoundException
	{
		final PublisherEntity publisherEntity = _publishersRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER,
						"Publisher with id: " + id + " doesn't exist"));

		return PublisherConverter.dtoFromEntity(publisherEntity);
	}

	public Long add(final PublisherDTO publisher) throws DuplicateResourceException
	{
		final PublisherEntity entity = PublisherConverter.entityFromDto(publisher);

		try
		{
			return _publishersRepository.save(entity).getId();
		} catch (final DataIntegrityViolationException e)
		{
			throw new DuplicateResourceException(ResourceType.PUBLISHER,
					"Publisher '" + publisher.getName() + "' already exists");
		}
	}

	public Long update(final Long id, final PublisherDTO publisher) throws ResourceNotFoundException
	{
		final PublisherEntity entityToUpdate = _publishersRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist"));

		entityToUpdate.setName(publisher.getName());

		return _publishersRepository.save(entityToUpdate).getId();
	}

	public void delete(final Long id) throws ResourceNotFoundException
	{
		if (!_publishersRepository.existsById(id))
		{
			throw new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id " + id + " does not exist");
		}

		_publishersRepository.deleteById(id);
	}
}
