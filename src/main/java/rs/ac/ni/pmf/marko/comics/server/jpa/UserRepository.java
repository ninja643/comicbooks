package rs.ac.ni.pmf.marko.comics.server.jpa;

import org.springframework.data.repository.CrudRepository;

import rs.ac.ni.pmf.marko.comics.server.datamodel.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
