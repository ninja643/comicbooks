package rs.ac.ni.pmf.marko.comics.server.rest;

import rs.ac.ni.pmf.marko.comics.server.exception.AbstractResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public enum ErrorId
{
	PUBLISHER_ALREADY_EXISTS,
	PUBLISHER_NOT_FOUND;

	public static ErrorId from(AbstractResourceException e)
	{
		if (e instanceof ResourceNotFoundException)
		{
			switch (e.getResourceType())
			{
			case COMIC_BOOK:
				break;
			case HERO:
				break;
			case PUBLISHER:
				return ErrorId.PUBLISHER_NOT_FOUND;
			case USER:
				break;
			default:
				break;
			}
		}

		if (e instanceof DuplicateResourceException)
		{
			switch (e.getResourceType())
			{
			case COMIC_BOOK:
				break;
			case HERO:
				break;
			case PUBLISHER:
				return ErrorId.PUBLISHER_ALREADY_EXISTS;
			case USER:
				break;
			default:
				break;
			}
		}

		throw new UnsupportedOperationException("Not implemented, yet");
	}

}
