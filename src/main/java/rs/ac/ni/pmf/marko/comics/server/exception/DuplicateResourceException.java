package rs.ac.ni.pmf.marko.comics.server.exception;

public class DuplicateResourceException extends AbstractResourceException
{
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(final ResourceType resourceType, final String message)
	{
		super(resourceType, message);
	}
}
