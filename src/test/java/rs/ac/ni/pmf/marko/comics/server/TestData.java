package rs.ac.ni.pmf.marko.comics.server;

import rs.ac.ni.pmf.marko.comics.server.model.entity.ComicBookEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.HeroEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublisherEntity;
import rs.ac.ni.pmf.marko.comics.server.model.entity.PublishersSeriesEntity;

import java.util.Arrays;

public final class TestData
{
	// ============================================================================
	// ================================== Heroes ==================================
	// ============================================================================

	public static final HeroEntity ZAGOR = HeroEntity.builder()
			.id(1L)
			.name("Zagor")
			.build();

	public static final HeroEntity TEX = HeroEntity.builder()
			.id(2L)
			.name("Tex Viler")
			.build();

	// ============================================================================
	// ================================ Publishers ================================
	// ============================================================================

	public static final PublisherEntity VC = PublisherEntity.builder()
			.id(1L)
			.name("Veseli četvrtak")
			.build();

	public static final PublisherEntity ZS = PublisherEntity.builder()
			.id(1L)
			.name("Zlatna serija")
			.build();

	// ============================================================================
	// ================================== SERIES ==================================
	// ============================================================================

	public static final PublishersSeriesEntity VC_STANDARD = PublishersSeriesEntity.builder()
			.id(1L)
			.publisher(VC)
			.series("Standard")
			.isDefault(true)
			.build();

	public static final PublishersSeriesEntity VC_SPECIJAL = PublishersSeriesEntity.builder()
			.id(2L)
			.publisher(VC)
			.series("Specijal")
			.isDefault(false)
			.build();

	public static final PublishersSeriesEntity VC_COLOR = PublishersSeriesEntity.builder()
			.id(3L)
			.publisher(VC)
			.series("Obojeni program")
			.build();

	public static final PublishersSeriesEntity ZS_STANDARD = PublishersSeriesEntity.builder()
			.id(4L)
			.publisher(ZS)
			.series("Standard")
			.isDefault(true)
			.build();

	// ============================================================================
	// ================================== COMICS ==================================
	// ============================================================================

	public static final ComicBookEntity ZAGOR_VC_1 = ComicBookEntity.builder()
			.id(1L)
			.number(1)
			.title("Tajna Sumera")
			.publisherSeries(VC_STANDARD)
			.heroes(Arrays.asList(ZAGOR))
			.build();

	public static final ComicBookEntity ZAGOR_VC_2 = ComicBookEntity.builder()
			.id(2L)
			.number(2)
			.title("Teror u muzeju")
			.publisherSeries(VC_COLOR)
			.heroes(Arrays.asList(ZAGOR))
			.build();

	public static final ComicBookEntity ZAGOR_VC_SPECIJAL_31 = ComicBookEntity.builder()
			.id(3L)
			.number(31)
			.title("Knjiga senki")
			.publisherSeries(VC_SPECIJAL)
			.heroes(Arrays.asList(ZAGOR))
			.build();

	public static final ComicBookEntity TEX_VC_36 = ComicBookEntity.builder()
			.id(4L)
			.number(36)
			.title("Neumoljivi Tumak")
			.publisherSeries(VC_STANDARD)
			.heroes(Arrays.asList(TEX))
			.build();

	public static final ComicBookEntity[] comicBookEntities = new ComicBookEntity[]{
			ZAGOR_VC_1, ZAGOR_VC_2, ZAGOR_VC_SPECIJAL_31, TEX_VC_36
	};


}
