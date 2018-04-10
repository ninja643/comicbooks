package rs.ac.ni.pmf.marko.comics.server.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;

@Repository
public interface ComicBooksRepository extends CrudRepository<ComicBook, Long> 
{
	List<ComicBook> findAll();
}
