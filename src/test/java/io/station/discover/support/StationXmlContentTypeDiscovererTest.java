package io.station.discover.support;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.StationXmlDiscovererTest;
import io.station.discover.discriptor.DefaultContentTypeDescriptor;

public class StationXmlContentTypeDiscovererTest {

	@Test
	public void d() throws Exception {

		StationXmlContentTypeDiscoverer stationXmlContentTypeDiscoverer = new StationXmlContentTypeDiscoverer();
		try (InputStream inputStream = StationXmlDiscovererTest.class.getClassLoader().getResourceAsStream("iu.xml");) {

			ContentTypeDescriptor contentTypeDescriptor = stationXmlContentTypeDiscoverer.discover(inputStream);
			assertNotNull(contentTypeDescriptor);

			assertEquals(DefaultContentTypeDescriptor.stationXmlTypeDescriptor(), contentTypeDescriptor);
		}
	}
}
