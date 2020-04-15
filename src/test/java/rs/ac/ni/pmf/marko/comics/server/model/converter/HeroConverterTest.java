package rs.ac.ni.pmf.marko.comics.server.model.converter;

import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;
import rs.ac.ni.pmf.marko.comics.server.TestData;
import rs.ac.ni.pmf.marko.comics.server.model.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroConverterTest
{
	@Tested
	private HeroConverter _heroConverter;

	@Test
	public void shouldConvertHeroEntity(@Injectable("Zagor") String name)
	{
		final HeroDTO expectedHeroDto = HeroDTO.builder()
				.id(TestData.TEX.getId())
				.name(TestData.TEX.getName())
				.build();

		final HeroDTO actualHeroDto = _heroConverter.dtoFromEntity(TestData.TEX);

		assertThat(actualHeroDto).isEqualTo(expectedHeroDto);
	}
}
