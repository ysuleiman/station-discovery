package io.station.discover;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream extends OutputStream {

	public NullOutputStream() {
	}

	@Override
	public void write(int b) throws IOException {

	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void close() throws IOException {

	}

}
