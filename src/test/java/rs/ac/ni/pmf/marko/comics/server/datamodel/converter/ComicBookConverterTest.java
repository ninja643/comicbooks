package rs.ac.ni.pmf.marko.comics.server.datamodel.converter;

import org.junit.Test;
import rs.ac.ni.pmf.marko.comics.server.datamodel.api.ComicBookDTO;
import rs.ac.ni.pmf.marko.comics.server.datamodel.entity.ComicBookEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ComicBookConverterTest {

    @Test
    public void shouldCreateDtoFromEntity() {
        final ComicBookEntity comicBookEntity = ComicBookEntity.builder().id(1L).build();

        final ComicBookDTO expectedDto = ComicBookDTO.builder().id(1L).build();

        final ComicBookDTO actualDto = ComicBookConverter.dtoFromEntity(comicBookEntity);

        assertThat(actualDto).isEqualTo(expectedDto);
    }
}