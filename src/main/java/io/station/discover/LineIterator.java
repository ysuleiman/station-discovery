package io.station.discover;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LineIterator implements Iterator<String>, Closeable {

	private BufferedReader bufferedReader;
	private String line;

	public LineIterator(Reader reader) {
		if (reader == null) {
			throw new IllegalArgumentException("Reader cannot be null");
		}
		if (reader instanceof BufferedReader) {
			this.bufferedReader = (BufferedReader) reader;
		} else {
			this.bufferedReader = new BufferedReader(reader);
		}
	}

	@Override
	public boolean hasNext() {
		if (line != null) {
			return true;
		} else {
			try {
				line = bufferedReader.readLine();
				if (line == null) {
					return false;
				} else {
					return true;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException("End of file");
		} else {
			String temp = line;
			line = null;
			return temp;
		}
	}

	@Override
	public void close() throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}

	}
}
