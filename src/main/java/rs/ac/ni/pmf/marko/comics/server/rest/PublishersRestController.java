package rs.ac.ni.pmf.marko.comics.server.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.PublisherSeriesDTO;

import java.util.List;

@Api
@RequestMapping("/publishers")
public interface PublishersRestController
{
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PublisherDTO> getAll();

	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PublisherDTO getById(@PathVariable(name = "id") final long id) throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long add(@ApiParam(value = "Publisher to add", required = true) @RequestBody final PublisherDTO publisher) throws DuplicateResourceException;

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") final long id) throws ResourceNotFoundException;

	@RequestMapping(path = "/update/{id}",
					method = RequestMethod.PUT,
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long update(@PathVariable(name = "id") final Long id, @RequestBody final PublisherDTO publisher)
			throws ResourceNotFoundException, BadRequestException;

	@GetMapping(path = "/{publisher_id}/series", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PublisherSeriesDTO> getPublisherSeries(@PathVariable(name = "publisher_id") final long publisherId);

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/{publisher_id}/series")
	public Long createSeries(@PathVariable(name = "publisher_id") final long publisherId, @RequestBody final PublisherSeriesDTO publisherSeriesDTO)
			throws ResourceNotFoundException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{publisher_id}/series/{series_id}")
	public void updateSeries(@PathVariable(name = "publisher_id") final long publisherId,
							 @PathVariable(name = "series_id") final Long seriesId,
							 @RequestBody final PublisherSeriesDTO publisherSeriesDTO) throws ResourceNotFoundException, BadRequestException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping(path = "/{publisher_id}/series/{series_id}/default")
	public void setAsDefaultSeries(@PathVariable(name = "publisher_id") final long publisherId, @PathVariable(name = "series_id") final Long seriesId)
			throws ResourceNotFoundException, BadRequestException;

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{publisher_id}/series/{series_id}")
	public void deleteSeries(@PathVariable(name = "publisher_id") final long publisherId, @PathVariable(name = "series_id") final Long seriesId)
			throws ResourceNotFoundException, BadRequestException;

}
