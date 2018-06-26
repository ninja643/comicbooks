package rs.ac.ni.pmf.marko.comics.server.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.PublisherEntity;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Long>
{

}
