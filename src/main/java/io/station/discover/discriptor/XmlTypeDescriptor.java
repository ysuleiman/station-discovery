package io.station.discover.discriptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.station.discover.ContentTypeDescriptor;

public class XmlTypeDescriptor extends AbstractTypeDescriptor{
	public XmlTypeDescriptor(String resolvableType) {
		super(resolvableType);
	}
	@Override
	public Charset getCharset() {
		return StandardCharsets.UTF_8;
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMediaType() {
		return "application/xml";
	}

}