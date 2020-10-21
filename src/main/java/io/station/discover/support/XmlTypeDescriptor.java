package io.station.discover.support;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.station.discover.ContentTypeDescriptor;

public class XmlTypeDescriptor implements ContentTypeDescriptor {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResolvableType() {
		// TODO Auto-generated method stub
		return null;
	}
}
