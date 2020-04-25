package rs.ac.ni.pmf.marko.comics.server.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;

import java.util.List;

@Repository
public interface ComicBooksRepository extends CrudRepository<ComicBookEntity, Long>, JpaSpecificationExecutor<ComicBookEntity>
{
	@Override
	List<ComicBookEntity> findAll();
}
