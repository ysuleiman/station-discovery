package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;
import io.station.discover.discriptor.DefaultContentTypeDescriptor;

public class StationXmlContentTypeDiscoverer extends XmlContentTypeDiscoverer {

	@Override
	public ContentTypeDescriptor discover(InputStream input) throws UnkownContentTypeException, IOException {
		return discover(input, new StationXmlMetadata());
	}

	@Override
	public ContentTypeDescriptor getTypeDescriptor() {
		return DefaultContentTypeDescriptor.stationXmlTypeDescriptor();
	}
	@Override
	public Metadata getMetadata() {
		return new StationXmlMetadata();
	}
	private static class StationXmlMetadata implements Metadata {

		@Override
		public Object getMagic() {
			QName qname = new QName("http://www.fdsn.org/xml/station/1", "FDSNStationXML");
			return qname;
		}

		@Override
		public int getRecomendedNumberOfBytesToRead() {
			return 200;
		}

	}

	
}
