package io.station.discover.support;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.regex.Pattern;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;
import io.station.discover.discriptor.SacpzTypeDescriptor;

public class SacpzContentTypeDiscoverer extends AsciiContentTypeDiscoverer {

	private Metadata metadata = new SacpzMetadata();

	@Override
	public ContentTypeDescriptor getTypeDescriptor() {
		return new SacpzTypeDescriptor();
	}

	@Override
	public Metadata getMetadata() {
		return metadata;
	}

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream) throws UnkownContentTypeException, IOException {
		return discover(inputStream, metadata);
	}

	private static class SacpzMetadata implements Metadata {
		@Override
		public Object getMagic() {
			return Pattern.compile("^\\*.*\\n*^\\* NETWORK", Pattern.MULTILINE);
		}

		@Override
		public int getRecomendedNumberOfBytesToRead() {
			return 100;
		}
	}
}
