package io.station.discover.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.discriptor.DefaultContentTypeDescriptor;

public class SacpzContentTypeDiscovererTest {

	@Test
	public void d() throws Exception {

		SacpzContentTypeDiscoverer sacpzContentTypeDiscoverer = new SacpzContentTypeDiscoverer();
		try (InputStream inputStream = SacpzContentTypeDiscovererTest.class.getClassLoader().getResourceAsStream("iu.anmo.bhz.sacpz");) {

			ContentTypeDescriptor contentTypeDescriptor = sacpzContentTypeDiscoverer.discover(inputStream);
			assertNotNull(contentTypeDescriptor);

			assertEquals(DefaultContentTypeDescriptor.sacpzTypeDescriptor(),contentTypeDescriptor);
		}
	}
}
