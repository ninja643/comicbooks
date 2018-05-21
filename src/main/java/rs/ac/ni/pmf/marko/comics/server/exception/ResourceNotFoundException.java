package rs.ac.ni.pmf.marko.comics.server.exception;

public class ResourceNotFoundException extends AbstractResourceException
{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final ResourceType resourceType, final String message)
	{
		super(resourceType, message);
	}
}
