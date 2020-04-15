package rs.ac.ni.pmf.marko.comics.server.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import rs.ac.ni.pmf.marko.comics.server.model.PaperSize;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@ApiModel(value = "Comic book DTO", description = "The basic comic book API response object")
public class ComicBookDTO
{
	@ApiModelProperty(value = "The unique ID of the comic book")
	private Long id;

	@ApiModelProperty(example = "13", value = "The number of the published comic book")
	private int number;

	@ApiModelProperty(example = "Nasilje u Darkvudu", value = "Title of the comic book")
	private String title;

	@ApiModelProperty(example = "/images/front/13.png", value = "URL to the front page image")
	private String frontPageUrl;

	private boolean paperback;

	private PaperSize paperSize;

	private Long seriesId;

	private String seriesName;

	private String publisher;

	@ApiModelProperty(value = "The list of heroes that appear in this comic book")
	private Set<HeroDTO> heroes;
}
