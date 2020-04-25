package rs.ac.ni.pmf.marko.comics.server.rest.errors;

import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

public enum ErrorId
{
	PUBLISHER_ALREADY_EXISTS,
	PUBLISHER_NOT_FOUND,

	COMIC_BOOK_ID_ALREADY_EXISTS,
	COMIC_BOOK_NOT_FOUND,

	HERO_ALREADY_EXISTS,

	PERSISTENCE_ERROR,
	GENERAL_ERROR;

	public static ErrorId from(final ResourceNotFoundException e)
	{
		switch (e.getResourceType())
		{
			case COMIC_BOOK:
				return COMIC_BOOK_ID_ALREADY_EXISTS;
			case HERO:
				return HERO_ALREADY_EXISTS;
			case PUBLISHER:
				return PUBLISHER_NOT_FOUND;
			case USER:
				break;
			default:
				break;
		}

		return GENERAL_ERROR;
	}

	public static ErrorId from(final DuplicateResourceException e)
	{
		switch (e.getResourceType())
		{
			case COMIC_BOOK:
				return COMIC_BOOK_NOT_FOUND;
			case HERO:
				break;
			case PUBLISHER:
				return PUBLISHER_ALREADY_EXISTS;
			case USER:
				break;
			default:
				break;
		}

		return GENERAL_ERROR;
	}
}
