package rs.ac.ni.pmf.marko.comics.server.exception;

public class BadRequestException extends AbstractResourceException
{
	public BadRequestException(ResourceType resourceType, String message)
	{
		super(resourceType, message);
	}
}
