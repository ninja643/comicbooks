package rs.ac.ni.pmf.marko.comics.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.marko.comics.server.exception.*;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherSeriesDTO;
import rs.ac.ni.pmf.marko.comics.server.model.converter.PublisherConverter;
import rs.ac.ni.pmf.marko.comics.server.model.converter.PublisherSeriesConverter;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublishersSeriesEntity;
import rs.ac.ni.pmf.marko.comics.server.repository.PublisherSeriesRepository;
import rs.ac.ni.pmf.marko.comics.server.repository.PublishersRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublishersService
{
	private final PublishersRepository _publishersRepository;
	private final PublisherSeriesRepository _publisherSeriesRepository;

	private final PublisherConverter _publisherConverter;
	private final PublisherSeriesConverter _publisherSeriesConverter;

	public List<PublisherDTO> getAll()
	{
		final List<PublisherEntity> entities = _publishersRepository.findAll();
		return entities.stream().map(publisherEntity -> _publisherConverter.dtoFromEntity(publisherEntity)).collect(Collectors.toList());
	}

	public PublisherDTO get(final Long id) throws ResourceNotFoundException
	{
		final PublisherEntity publisherEntity = _publishersRepository.findById(id)
				.orElseThrow(() -> ExceptionHelper.publisherNotFound(id));

		return _publisherConverter.dtoFromEntity(publisherEntity);
	}

	public Long add(final PublisherDTO publisher) throws DuplicateResourceException
	{
		final PublisherEntity entity = _publisherConverter.entityFromDto(publisher);

		try
		{
			return _publishersRepository.save(entity).getId();
		}
		catch (final DataIntegrityViolationException e)
		{
			throw ExceptionHelper.duplicatePublisher(publisher.getName());
		}
	}

	public Long update(final Long id, final PublisherDTO publisher) throws ResourceNotFoundException
	{
		final PublisherEntity entityToUpdate = _publishersRepository.findById(id)
				.orElseThrow(() -> ExceptionHelper.publisherNotFound(id));

		entityToUpdate.setName(publisher.getName());

		return _publishersRepository.save(entityToUpdate).getId();
	}

	public void delete(final Long id) throws ResourceNotFoundException
	{
		if (!_publishersRepository.existsById(id))
		{
			throw ExceptionHelper.publisherNotFound(id);
		}

		_publishersRepository.deleteById(id);
	}

	@Transactional
	public List<PublisherSeriesDTO> getPublisherSeries(final Long publisherId)
	{
		return _publisherSeriesRepository.findAllByPublisherId(publisherId)
				.map(_publisherSeriesConverter::toDto)
				.collect(Collectors.toList());
	}

	@Transactional
	public Long createSeries(final long publisherId, final PublisherSeriesDTO publisherSeriesDTO) throws ResourceNotFoundException
	{
		final PublisherEntity publisherEntity = _publishersRepository.findById(publisherId)
				.orElseThrow(() -> ExceptionHelper.publisherNotFound(publisherId));

		final List<PublishersSeriesEntity> series = publisherEntity.getSeries();

		final boolean setAsDefault = publisherSeriesDTO.isDefaultSeries() || series.isEmpty();

		final PublisherEntity updatedPublisherEntity = publisherSeriesDTO.isDefaultSeries() ? clearDefaultForSeries(publisherEntity) : publisherEntity;

		final PublishersSeriesEntity publishersSeriesEntity = _publisherSeriesConverter.toEntity(publisherSeriesDTO, updatedPublisherEntity);

		if (setAsDefault)
		{
			publishersSeriesEntity.setDefaultSeries(true);
		}

		return _publisherSeriesRepository.save(publishersSeriesEntity).getId();
	}

	private PublisherEntity clearDefaultForSeries(final PublisherEntity publisherEntity)
	{
		final List<PublishersSeriesEntity> series = publisherEntity.getSeries();

		if (!series.isEmpty())
		{
			series.stream().forEach(pse -> pse.setDefaultSeries(false));
			return _publishersRepository.save(publisherEntity);
		}

		return publisherEntity;
	}

	private PublishersSeriesEntity checkPublishersSeriesEntity(final long publisherId, final long seriesId)
			throws ResourceNotFoundException, BadRequestException
	{
		final PublishersSeriesEntity entity = _publisherSeriesRepository.findById(seriesId)
				.orElseThrow(() -> ExceptionHelper.seriesNotFound(seriesId));

		if (entity.getPublisher().getId() != publisherId)
		{
			throw new BadRequestException(ResourceType.SERIES, String.format("Wrong (publisherId, seriesId) combination (%ld, %ld)", publisherId, seriesId));
		}
		return entity;
	}

	public void deleteSeries(final long publisherId, final long seriesId) throws ResourceNotFoundException, BadRequestException
	{
		_publisherSeriesRepository.delete(checkPublishersSeriesEntity(publisherId, seriesId));
	}

	@Transactional
	public void updateSeries(final long publisherId, final Long seriesId, final PublisherSeriesDTO publisherSeriesDTO)
			throws ResourceNotFoundException, BadRequestException
	{
		final PublishersSeriesEntity seriesEntity = checkPublishersSeriesEntity(publisherId, seriesId);

		seriesEntity.setSeries(publisherSeriesDTO.getSeries());

		_publisherSeriesRepository.save(seriesEntity);
	}

	@Transactional
	public void setAsDefaultSeries(final long publisherId, final long seriesId) throws ResourceNotFoundException, BadRequestException
	{
		final PublishersSeriesEntity defaultSeriesEntity = checkPublishersSeriesEntity(publisherId, seriesId);

		if (defaultSeriesEntity.isDefaultSeries())
		{
			// Nothing to do. Series is already a default one
			return;
		}

		final PublisherEntity publisherEntity = defaultSeriesEntity.getPublisher();

		for (final PublishersSeriesEntity publishersSeriesEntity : publisherEntity.getSeries())
		{
			publishersSeriesEntity.setDefaultSeries(publishersSeriesEntity.getId() == defaultSeriesEntity.getId());
		}

		_publishersRepository.save(publisherEntity);
	}
}
