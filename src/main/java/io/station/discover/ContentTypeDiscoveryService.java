package io.station.discover;

import java.io.IOException;
import java.nio.file.Path;

public interface ContentTypeDiscoveryService extends Discoverer{

	ContentTypeDescriptor discover(Path file) throws UnkownContentTypeException, IOException;
	ContentTypeDescriptor discover(Path file,Metadata metatada) throws UnkownContentTypeException, IOException;
	
}
