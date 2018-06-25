package rs.ac.ni.pmf.marko.comics.server.api;


import java.util.List;

import lombok.Value;
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.datamodel.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.datamodel.PublisherEntity;

@Value
public class ComicBookDTO {

	private Long id;
	
	private int version;
	
	private int number;
	
	private String title;
	
	private String frontPageUrl;
	
	private PublisherEntity publisher;
	
	private List<HeroEntity> heroes; 
	
	private ComicBookDTO fromEntity(ComicBookEntity comicBookEntity) 
	{
		return new ComicBookDTO(comicBookEntity.getId(), comicBookEntity.getVersion(),
				comicBookEntity.getNumber(), comicBookEntity.getTitle(),
				comicBookEntity.getFrontPageUrl(), comicBookEntity.getPublisher(), 
				comicBookEntity.getHeroes());
	}
	
	public ComicBookEntity toEntity()
	{
		return new ComicBookEntity(this.getId(), this.getVersion(), this.getNumber(),
				this.getTitle(), this.getFrontPageUrl(), this.getPublisher(), this.getHeroes());
	}
	
}
