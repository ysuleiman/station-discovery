package io.station.discover;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RewindableLineIterator implements Iterator<String>, Closeable {

	private BufferedReader bufferedReader;

	private String previous;
	private String line;
	private String next;

	private boolean finished = false;

	public RewindableLineIterator(Reader reader) {
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
		if (finished) {
			return false;
		} else if (line != null) {
			return true;
		} else if (next != null) {
			line = next;
			next = null;
			return true;
		} else {
			try {
				line = bufferedReader.readLine();
				if (line == null) {
					finished = true;
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
			previous = line;
			line = null;
			return previous;
		}
	}

	public String peek() {
		if (!hasNext()) {
			throw new NoSuchElementException("End of file");
		} else {
			return line;
		}
	}

	public void rewind() {
		if (previous == null) {
			throw new NoSuchElementException("Begining of file");
		}
		next = line;
		line = previous;
		previous = null;
		if(line!=null) {
			finished=false;
		}
	}

	@Override
	public void close() throws IOException {
		if (bufferedReader != null) {
			bufferedReader.close();
		}

	}
}
