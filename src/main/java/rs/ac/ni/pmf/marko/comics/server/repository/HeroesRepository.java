package rs.ac.ni.pmf.marko.comics.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;

import java.util.List;

@Repository
public interface HeroesRepository extends CrudRepository<HeroEntity, Long>
{
	@Override
	List<HeroEntity> findAll();
}
