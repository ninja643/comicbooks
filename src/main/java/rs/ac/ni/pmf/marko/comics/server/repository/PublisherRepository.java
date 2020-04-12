package rs.ac.ni.pmf.marko.comics.server.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Long>
{
	@Override
	List<PublisherEntity> findAll();
}
