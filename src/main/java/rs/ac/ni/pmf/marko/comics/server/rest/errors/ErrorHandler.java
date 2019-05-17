package rs.ac.ni.pmf.marko.comics.server.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import rs.ac.ni.pmf.marko.comics.server.exception.*;

@ControllerAdvice
public class ErrorHandler
{
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleResourceNotFoundException(final ResourceNotFoundException e)
	{
		return new ErrorInfo(ErrorId.from(e), e.getMessage());
	}

	@ExceptionHandler({DuplicateResourceException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleDuplicateResourceException(final DuplicateResourceException e)
	{
		return new ErrorInfo(ErrorId.from(e), e.getMessage());
	}


	@ExceptionHandler({BadRequestException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo handleBadResourceException(final BadRequestException e)
	{
		return new ErrorInfo(ErrorId.GENERAL_ERROR, e.getMessage());
	}
}