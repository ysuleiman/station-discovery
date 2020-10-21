package io.station.discover;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import io.station.discover.LineIterator;
import io.station.discover.RewindableLineIterator;

public class LineIteratorTest {

	@Test
	public void empty() throws Exception {
		StringReader reader = new StringReader("");
		try (LineIterator it = new LineIterator(reader);) {
			assertFalse(it.hasNext());
		}
	}

	@Test
	public void read() throws Exception {
		String lines = "Line One\nLine Two\nLine Three";
		StringReader reader = new StringReader(lines);
		try (RewindableLineIterator it = new RewindableLineIterator(reader);) {
			assertTrue(it.hasNext());
			String line = it.next();
			assertEquals("Line One", line);

			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line Two", line);

			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line Three", line);

			assertFalse(it.hasNext());
		}
	}

	@Test
	public void rewind() throws Exception {
		String lines = "Line One\nLine Two\nLine Three";
		StringReader reader = new StringReader(lines);
		try (RewindableLineIterator it = new RewindableLineIterator(reader);) {

			assertThrows(NoSuchElementException.class, () -> {
				it.rewind();
			});
			assertTrue(it.hasNext());
			assertThrows(NoSuchElementException.class, () -> {
				it.rewind();
			});
			
			String line = it.next();
			assertEquals("Line One", line);
			it.rewind();
			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line One", line);

			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line Two", line);

			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line Three", line);

			assertFalse(it.hasNext());
			it.rewind();
			assertTrue(it.hasNext());
			line = it.next();
			assertEquals("Line Three", line);
		}
	}
}
