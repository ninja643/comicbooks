package rs.ac.ni.pmf.marko.comics.server.exception;

public final class ExceptionHelper
{
	public static ResourceNotFoundException publisherNotFound(final long publisherId)
	{
		return new ResourceNotFoundException(ResourceType.PUBLISHER, "Publisher with id '" + publisherId + "' not found");
	}

	public static ResourceNotFoundException seriesNotFound(final long seriesId)
	{
		return new ResourceNotFoundException(ResourceType.SERIES, "Publisher series with id '" + seriesId + "' not found");
	}

	public static DuplicateResourceException duplicatePublisher(final String name)
	{
		return new DuplicateResourceException(ResourceType.PUBLISHER, "Publisher '" + name + "' already exists");
	}
}
