package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;


public class QuakemlContentTypeDiscoverer extends XmlContentTypeDiscoverer {

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream) throws UnkownContentTypeException, IOException {
		return discover(inputStream, new QuakemlMetadata());
	}

	@Override
	public ContentTypeDescriptor getTypeDescriptor() {
		return new QuakemlTypeDescriptor();
	}
	@Override
	public Metadata getMetadata() {
		return new QuakemlMetadata();
	}
	private static class QuakemlMetadata implements Metadata {

		@Override
		public Object getMagic() {
			return new QName("http://quakeml.org/xmlns/quakeml/1.2", "quakeml");
		}

		@Override
		public int getRecomendedNumberOfBytesToRead() {
			return 100;
		}

	}

	private static class QuakemlTypeDescriptor extends XmlTypeDescriptor {

	}

	
}
