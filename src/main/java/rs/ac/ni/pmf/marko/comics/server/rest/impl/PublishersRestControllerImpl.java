package rs.ac.ni.pmf.marko.comics.server.rest.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceType;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.service.PublishersService;
import rs.ac.ni.pmf.marko.comics.server.rest.PublishersRestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublishersRestControllerImpl implements PublishersRestController
{
	private final PublishersService _publishersService;

	@Override
	public List<PublisherDTO> getAll()
	{
		return _publishersService.getAll();
	}

	@Override
	public PublisherDTO getById(final long id) throws ResourceNotFoundException
	{
		return _publishersService.get(id);
	}

	@Override
	public Long add(final PublisherDTO publisher) throws DuplicateResourceException
	{
		return _publishersService.add(publisher);
	}

	@Override
	public void delete(final long id) throws ResourceNotFoundException
	{
		_publishersService.delete(id);
	}

	@Override
	public Long update(final Long id, final PublisherDTO publisher) throws ResourceNotFoundException, BadRequestException
	{
		if (publisher.getId() != null && publisher.getId() != id)
		{
			throw new BadRequestException(ResourceType.PUBLISHER, "Publisher id cannot be changed. " +
					"You should not supply it in the request body");
		}
		return _publishersService.update(id, publisher);
	}
}
