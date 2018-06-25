package rs.ac.ni.pmf.marko.comics.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rs.ac.ni.pmf.marko.comics.server.datamodel.UserEntity;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;
import rs.ac.ni.pmf.marko.comics.server.provider.UserProvider;

@RestController
@Api
@RequestMapping(value = "/user")
public class UserRestService {

	@Autowired
	UserProvider dataProvider;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Iterable<UserEntity> getAll()
	{
		return dataProvider.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity getById(@RequestParam(name = "id") Long id) throws ResourceNotFoundException
	{
		return dataProvider.get(id);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity add(@RequestBody UserEntity user)
	{
		return dataProvider.add(user);
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserEntity update(@RequestParam(name = "id")Long id, @RequestBody UserEntity userEntity)
	{
		return dataProvider.updateUser(id, userEntity);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestParam(name = "id")Long id) throws ResourceNotFoundException
	{
		dataProvider.deleteUser(id);
	}
	
}







