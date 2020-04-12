package rs.ac.ni.pmf.marko.comics.server.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;

@Repository
public interface ComicBooksRepository extends CrudRepository<ComicBookEntity, Long>, JpaSpecificationExecutor<ComicBookEntity>
{
	@Override
	List<ComicBookEntity> findAll();
}
