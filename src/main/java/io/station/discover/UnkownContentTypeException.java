package io.station.discover;

public class UnkownContentTypeException extends Exception {

	public UnkownContentTypeException(Throwable t) {
		super(t);
	}

	public UnkownContentTypeException(String message, Throwable t) {
		super(message, t);
	}

	public UnkownContentTypeException(String message, Throwable t, Object obj) {
		super(message, t);
	}

	public UnkownContentTypeException(String message) {
		super(message);
	}

	public UnkownContentTypeException(String message, Object obj) {
		super(message);
	}
}
