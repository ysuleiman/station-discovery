package io.station.discover;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import io.station.discover.support.StationXmlContentTypeDiscoverer;


public class StationXmlDiscovererTest {

	@Test
	public void d() throws Exception {
		try (InputStream inputStream = StationXmlDiscovererTest.class.getClassLoader().getResourceAsStream("iu.xml");) {
			StationXmlContentTypeDiscoverer d = new StationXmlContentTypeDiscoverer();
			d.discover(inputStream, null);
		}
	}
}
