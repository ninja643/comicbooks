package rs.ac.ni.pmf.marko.comics.server.jpa;

import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface HeroRepository extends CrudRepository <HeroEntity, Long> {

	
}
