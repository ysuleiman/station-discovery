package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Discoverer;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;

public abstract class XmlContentTypeDiscoverer implements Discoverer {

	public abstract ContentTypeDescriptor getTypeDescriptor();

	public abstract Metadata getMetadata();

	@Override
	public ContentTypeDescriptor discover(InputStream input, Metadata metadata)
			throws UnkownContentTypeException, IOException {
		ExtractorHandler handler = new ExtractorHandler();
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(input, handler);
		} catch (Exception e) {
			// do nothing
		}
		QName qname = handler.rootElement;
		if (metadata == null) {
			metadata = this.getMetadata();
		}
		
		Object magic = metadata.getMagic();
		if (magic != null && magic instanceof QName) {
			if (magic.equals(qname)) {
				return getTypeDescriptor();
			}
		}
		throw new UnkownContentTypeException("" + qname);
	}

	protected static class ExtractorHandler extends DefaultHandler {

		private QName rootElement = null;

		@Override
		public void startElement(String uri, String local, String name, Attributes attributes) throws SAXException {
			this.rootElement = new QName(uri, local);
			throw new SAXException("Aborting: root element received");
		}

		QName getRootElement() {
			return rootElement;
		}
	}

}
