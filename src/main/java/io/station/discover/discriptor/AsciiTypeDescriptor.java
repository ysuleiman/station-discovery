package io.station.discover.discriptor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.station.discover.ContentTypeDescriptor;

public class AsciiTypeDescriptor extends AbstractTypeDescriptor{
	
	public AsciiTypeDescriptor(String type) {
		super(type);
	}
	@Override
	public Charset getCharset() {
		return StandardCharsets.US_ASCII;
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMediaType() {
		return "text/plain";
	}

}
