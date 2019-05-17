package rs.ac.ni.pmf.marko.comics.server.rest.errors;

import lombok.Value;

@Value
public class ErrorInfo
{
	ErrorId errorId;
	String message;
}
