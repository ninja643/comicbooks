package rs.ac.ni.pmf.marko.comics.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;

import java.util.List;

@Repository
public interface PublishersRepository extends CrudRepository<PublisherEntity, Long>
{
	@Override
	List<PublisherEntity> findAll();
}
