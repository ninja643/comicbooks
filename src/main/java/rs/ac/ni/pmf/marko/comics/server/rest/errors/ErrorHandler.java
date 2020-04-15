package rs.ac.ni.pmf.marko.comics.server.rest.errors;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.ni.pmf.marko.comics.server.exception.BadRequestException;
import rs.ac.ni.pmf.marko.comics.server.exception.DuplicateResourceException;
import rs.ac.ni.pmf.marko.comics.server.exception.ResourceNotFoundException;

import javax.persistence.PersistenceException;

@ControllerAdvice
@ResponseBody
public class ErrorHandler
{
	@ExceptionHandler(PersistenceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handlePersistenceException(final PersistenceException e)
	{
		if (e instanceof JDBCException)
		{
			final JDBCException jdbcException = (JDBCException) e;
			final String errorMessage = jdbcException.getSQLException() != null
					? jdbcException.getSQLException().getMessage()
					: "SQL error code: " + jdbcException.getErrorCode();

			return new ErrorInfo(ErrorId.PERSISTENCE_ERROR, errorMessage);
		}

		return new ErrorInfo(ErrorId.PERSISTENCE_ERROR, e.getMessage());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorInfo handleResourceNotFoundException(final ResourceNotFoundException e)
	{
		return new ErrorInfo(ErrorId.from(e), e.getMessage());
	}

	@ExceptionHandler({DuplicateResourceException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handleDuplicateResourceException(final DuplicateResourceException e)
	{
		return new ErrorInfo(ErrorId.from(e), e.getMessage());
	}


	@ExceptionHandler({BadRequestException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorInfo handleBadResourceException(final BadRequestException e)
	{
		return new ErrorInfo(ErrorId.GENERAL_ERROR, e.getMessage());
	}
}
