package rs.ac.ni.pmf.marko.comics.server.rest;

import lombok.Value;

@Value
public class ErrorInfo
{
	ErrorId errorId;
	String message;
}
