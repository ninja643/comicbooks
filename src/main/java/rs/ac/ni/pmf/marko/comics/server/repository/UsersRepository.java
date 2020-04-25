package rs.ac.ni.pmf.marko.comics.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import rs.ac.ni.pmf.marko.comics.server.model.entity.UserEntity;

import java.util.List;

//@Repository
@Deprecated
/**
 * @deprecated To be rewritten completely
 */
public interface UsersRepository extends CrudRepository<UserEntity, Long>
{

	@Override
	List<UserEntity> findAll();

	@Query(value = "" + "from UserEntity e where " + "e.firstName like %:firstName% or "
			+ "e.lastName like %:lastName% or " + "e.username like %:username% or " + "e.password like %:password% or "
			+ "e.email like %:email%")
	public Page<UserEntity> getByProperties(
			@Param(value = "firstName") String firstName,
			@Param(value = "lastName") String lastName,
			@Param(value = "username") String username,
			@Param(value = "password") String password,
			@Param(value = "email") String email,
			Pageable page);
}
