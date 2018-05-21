package rs.ac.ni.pmf.marko.comics.server.exception;

public abstract class AbstractResourceException extends Exception
{
	private static final long serialVersionUID = 1L;

	private final ResourceType _resourceType;

	public AbstractResourceException(final ResourceType resourceType, final String message)
	{
		super(message);
		_resourceType = resourceType;
	}

	public ResourceType getResourceType()
	{
		return _resourceType;
	}

}
