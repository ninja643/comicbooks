package rs.ac.ni.pmf.marko.comics.server.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

@Repository
public interface ComicBooksRepository extends CrudRepository<ComicBookEntity, Long>
{
	List<ComicBookEntity> findAll();
}
