package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import mockit.Tested;
import org.junit.Test;
import org.junit.runner.RunWith;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ComicBookConverterTest {

    @Tested
    private ComicBookConverter _converter;

    @Test
    public void shouldCreateDtoFromEntity() {
        final ComicBookEntity comicBookEntity = ComicBookEntity.builder().id(1L).build();

        final ComicBookDTO expectedDto = ComicBookDTO.builder().id(1L).build();

        final ComicBookDTO actualDto = _converter.dtoFromEntity(comicBookEntity);

        assertThat(actualDto).isEqualTo(expectedDto);
    }
}