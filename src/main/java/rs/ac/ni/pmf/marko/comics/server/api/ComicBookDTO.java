package rs.ac.ni.pmf.marko.comics.server.api;

import lombok.Value;
import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBookEntity;

@Value
public class ComicBookDTO {

	private Long id;
	
	private int version;
	
	private int number;
	
	private String title;
	
	private String frontPageUrl;
	
	public ComicBookDTO fromEntity(ComicBookEntity comicBookEntity) 
	{
		return new ComicBookDTO(comicBookEntity.getId(), comicBookEntity.getVersion(),
				comicBookEntity.getNumber(), comicBookEntity.getTitle(),
				comicBookEntity.getFrontPageUrl());
	}
	
	public ComicBookEntity toEntity()
	{
		return new ComicBookEntity(getId(), getVersion(), getNumber(),
				getTitle(), getFrontPageUrl());
	}
	
}
