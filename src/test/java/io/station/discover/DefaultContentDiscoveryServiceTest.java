package io.station.discover;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;

import org.junit.jupiter.api.Test;



public class DefaultContentDiscoveryServiceTest {

	@Test
	public void d() throws Exception {
		ContentTypeDiscoveryService contentTypeDiscoveryService=DefaultContentDiscoveryService.instance();
		try (InputStream inputStream = StationXmlDiscovererTest.class.getClassLoader().getResourceAsStream("iu.xml");) {
			
			ContentTypeDescriptor contentTypeDescriptor=contentTypeDiscoveryService.discover(inputStream);
			assertNotNull(contentTypeDescriptor);
		}
	}
}
