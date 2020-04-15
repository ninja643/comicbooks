package rs.ac.ni.pmf.marko.comics.server.repository;

import org.springframework.data.repository.CrudRepository;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublishersSeriesEntity;

import java.util.stream.Stream;

public interface PublisherSeriesRepository extends CrudRepository<PublishersSeriesEntity, Long>
{
}
