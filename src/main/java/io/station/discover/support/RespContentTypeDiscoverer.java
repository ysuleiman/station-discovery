package io.station.discover.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import io.station.discover.ContentTypeDescriptor;
import io.station.discover.Metadata;
import io.station.discover.UnkownContentTypeException;
import io.station.discover.discriptor.RespTypeDescriptor;

public class RespContentTypeDiscoverer extends AsciiContentTypeDiscoverer {

	private Metadata metadata = new RespMetadata();

	@Override
	public ContentTypeDescriptor getTypeDescriptor() {
		return new RespTypeDescriptor();
	}

	@Override
	public Metadata getMetadata() {
		return metadata;
	}

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream) throws UnkownContentTypeException, IOException {
		return discover(inputStream, metadata);
	}

	private static class RespMetadata implements Metadata {

		@Override
		public Object getMagic() {
			return Pattern.compile("(^#.*\\n)*^B050F03", Pattern.MULTILINE);
		}

		@Override
		public int getRecomendedNumberOfBytesToRead() {
			return 100;
		}
	}


}
