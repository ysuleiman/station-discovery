package io.station.discover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.station.discover.support.SacpzContentTypeDiscoverer;


public class SacpzContentTypeDiscovererTest {

	@Test
	public void match() throws Exception {

		SacpzContentTypeDiscoverer d = new SacpzContentTypeDiscoverer();
		Metadata md = d.getMetadata();
		Object obj = md.getMagic();
		assertNotNull(obj);
		assertTrue(obj instanceof Pattern);
		Pattern pattern = (Pattern) obj;
		Matcher matcher = pattern.matcher("* **********************************\n" + "* NETWORK   (KNETWK): IU");
		assertTrue(matcher.find());
	}

	@Test
	public void nomatch() throws Exception {

		SacpzContentTypeDiscoverer d = new SacpzContentTypeDiscoverer();
		Metadata md = d.getMetadata();
		Object obj = md.getMagic();
		assertNotNull(obj);
		assertTrue(obj instanceof Pattern);
		Pattern pattern = (Pattern) obj;
		Matcher matcher = pattern.matcher("-* **********************************\n" + "* NETWORK   (KNETWK): IU");
		assertFalse(matcher.find());
	}
}
