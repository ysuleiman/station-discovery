package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;
import io.station.discover.discriptor.DefaultContentTypeDescriptor;

public class SeedContentTypeDiscoverer extends AsciiContentTypeDiscoverer {

	private SeedMetadata seedMetadata = new SeedMetadata();

	@Override
	public ContentTypeDescriptor getTypeDescriptor() {
		return DefaultContentTypeDescriptor.seedTypeDescriptor();
	}

	@Override
	public Metadata getMetadata() {
		return seedMetadata;
	}

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream) throws UnkownContentTypeException, IOException {
		return discover(inputStream, seedMetadata);
	}

	private static class SeedMetadata implements Metadata {

		@Override
		public Object getMagic() {
			return Pattern.compile("^\\d{6}[VASTDRQM][\\s\\*]");
		}

		@Override
		public int getRecomendedNumberOfBytesToRead() {
			return 100;
		}
		
	}
}
