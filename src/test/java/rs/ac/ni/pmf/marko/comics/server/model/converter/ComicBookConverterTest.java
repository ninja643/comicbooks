package rs.ac.ni.pmf.marko.comics.server.model.converter;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;
import rs.ac.ni.pmf.marko.comics.server.TestData;
import rs.ac.ni.pmf.marko.comics.server.model.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ComicBookConverterTest
{
	@Injectable
	private HeroConverter _heroConverter;

	@Tested
	private ComicBookConverter _comicBookConverter;

	@Test
	public void shouldCreateDtoFromEntity(@Injectable final HeroDTO heroDTO)
	{
		new Expectations()
		{
			{
				_heroConverter.dtoFromEntity(TestData.ZAGOR_VC_1.getHeroes().get(0));
				result = heroDTO;
			}
		};

		final ComicBookEntity comicBookEntity = TestData.ZAGOR_VC_1;

		final ComicBookDTO expectedDto = ComicBookDTO.builder()
				.id(TestData.ZAGOR_VC_1.getId())
				.number(TestData.ZAGOR_VC_1.getNumber())
				.title(TestData.ZAGOR_VC_1.getTitle())
				.frontPageUrl(TestData.ZAGOR_VC_1.getFrontPageUrl())
				.seriesId(TestData.ZAGOR_VC_1.getPublisherSeries().getId())
				.seriesName(TestData.ZAGOR_VC_1.getPublisherSeries().getSeries())
				.paperSize(TestData.ZAGOR_VC_1.getPaperSize())
				.publisher(TestData.ZAGOR_VC_1.getPublisherSeries().getPublisher().getName())
				.paperback(TestData.ZAGOR_VC_1.isPaperback())
				.heroes(Stream.of(heroDTO).collect(Collectors.toSet()))
				.build();

		final ComicBookDTO actualDto = _comicBookConverter.dtoFromEntity(comicBookEntity);

		assertThat(actualDto).isEqualTo(expectedDto);
	}
}
