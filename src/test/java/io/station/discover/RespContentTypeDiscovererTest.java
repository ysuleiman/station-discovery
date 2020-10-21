package io.station.discover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.station.discover.support.RespContentTypeDiscoverer;


public class RespContentTypeDiscovererTest {

	@Test
	public void match() throws Exception {

		RespContentTypeDiscoverer d = new RespContentTypeDiscoverer();
		Metadata md = d.getMetadata();
		Object obj = md.getMagic();
		assertNotNull(obj);
		assertTrue(obj instanceof Pattern);
		Pattern pattern = (Pattern) obj;
		Matcher matcher = pattern
				.matcher("#\n" + "###################################################################################\n"
						+ "#\n" + "B050F03     Station:     ANMO");
		assertTrue(matcher.find());
	}

	@Test
	public void nomatch() throws Exception {

		RespContentTypeDiscoverer d = new RespContentTypeDiscoverer();
		Metadata md = d.getMetadata();
		Object obj = md.getMagic();
		assertNotNull(obj);
		assertTrue(obj instanceof Pattern);
		Pattern pattern = (Pattern) obj;
		Matcher matcher = pattern
				.matcher("#\n" + "###################################################################################\n"
						+ "-#\n" + "B050F03     Station:     ANMO");
		assertFalse(matcher.find());
		
		Pattern p=Pattern.compile("^#.*\\r\\n");
		System.out.println(p.toString());
		matcher =p.matcher("-#\n" + "###################################################################################\n"
				+ "#\n" + "B050F03     Station:     ANMO");
		//assertTrue(matcher.find());
		
		
		p=Pattern.compile("(^#.*\\n)*^B050F03",Pattern.MULTILINE);
		matcher =p.matcher("-#\n" + "###################################################################################\n"
				+ "#\n" + "B050F03     Station:     ANMO");
		assertTrue(matcher.find());
		matcher =p.matcher("B050F03     Station:     ANMO");
		assertTrue(matcher.find());
	}
}
