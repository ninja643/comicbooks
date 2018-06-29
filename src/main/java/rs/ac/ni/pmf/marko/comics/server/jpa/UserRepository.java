package rs.ac.ni.pmf.marko.comics.server.jpa;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{

	List<UserEntity> findAll();
	
	@Query(value = ""
			+ "from UserEntity e where "
			+ "e.firstName like %:firstName% or "
			+ "e.lastName like %:lastName% or "
			+ "e.username like %:username% or "
			+ "e.password like %:password% or "
			+ "e.email like %:email%"
			)
	public Page<UserEntity> getByProperties(@Param(value="firstName") String firstName, @Param(value="lastName")String lastName,
			@Param(value="username") String username, @Param(value="password")String password,
			@Param(value="email")String email, Pageable page);
}
