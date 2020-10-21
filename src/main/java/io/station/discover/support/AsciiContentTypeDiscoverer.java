package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Discoverer;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;

public abstract class AsciiContentTypeDiscoverer implements Discoverer {

	public abstract ContentTypeDescriptor getTypeDescriptor();

	public abstract Metadata getMetadata();

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream, Metadata metadata)
			throws UnkownContentTypeException, IOException {
		if (metadata == null) {
			metadata = this.getMetadata();
			if (metadata == null) {
				throw new IllegalArgumentException("metadata cannot be null");
			}
		}
		// BufferedInputStream bis=new BufferedInputStream(inputStream);
		InputStreamReader reader = new InputStreamReader(inputStream);
		char[] buffer = new char[100];
		
		int numberOfCharactersRead = reader.read(buffer);
		if (numberOfCharactersRead < 100) {
			new UnkownContentTypeException("");
		}
		Object obj = metadata.getMagic();
		if (obj == null || !(obj instanceof Pattern)) {
			new IllegalStateException("");
		}
		Pattern pattern = (Pattern) obj;
		Matcher matcher = pattern.matcher(new String(buffer));
		if (matcher.find()) {
			return this.getTypeDescriptor();
		}
		throw new UnkownContentTypeException("");

	}

}
