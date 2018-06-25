package rs.ac.ni.pmf.marko.comics.server.api;

import java.util.List;

import lombok.Value;
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;

@Value
public class HeroDTO {

	private long id;
	
	private int version;
	
	private String name;
	
	private List<ComicBookEntity> comicBooks;
	
	public static HeroDTO fromEntity(final HeroEntity heroEntity) 
	{
		return new HeroDTO(heroEntity.getId(), heroEntity.getVersion(),
				heroEntity.getName(), heroEntity.getComicBooks());
	}
	
	public static HeroEntity toEntity() 
	{
		return new HeroEntity(getId(), getVersion(), getName(), getComicBooks());
	}
	
}
