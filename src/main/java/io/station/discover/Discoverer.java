package io.station.discover;

import java.io.IOException;
import java.io.InputStream;

public interface Discoverer {
	ContentTypeDescriptor discover(InputStream input) throws UnkownContentTypeException, IOException;

	ContentTypeDescriptor discover(InputStream input, Metadata metadata) throws UnkownContentTypeException, IOException;

}
