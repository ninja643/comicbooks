package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import static org.assertj.core.api.Assertions.assertThat;

import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.HeroDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.HeroEntity;

import java.util.Collections;

public class HeroConverterTest
{
	@Tested
	private HeroConverter _converter;

	@Test
	public void shouldConvertHeroEntity(@Injectable("Zagor") String name)
	{
		final HeroEntity heroEntity = HeroEntity.builder().id(1L).name(name).comicBooks(Collections.emptyList()).build();
		final HeroDTO expectedHeroDto = HeroDTO.builder().id(1L).name(name).build();

		final HeroDTO actualHeroDto = _converter.dtoFromEntity(heroEntity);

		assertThat(actualHeroDto).isEqualTo(expectedHeroDto);
	}
}
