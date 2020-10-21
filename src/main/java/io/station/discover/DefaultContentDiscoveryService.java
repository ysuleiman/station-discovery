package io.station.discover;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.station.discover.support.QuakemlContentTypeDiscoverer;
import io.station.discover.support.RespContentTypeDiscoverer;
import io.station.discover.support.SacpzContentTypeDiscoverer;
import io.station.discover.support.SeedContentTypeDiscoverer;
import io.station.discover.support.StationXmlContentTypeDiscoverer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultContentDiscoveryService implements ContentTypeDiscoveryService {

	private List<Discoverer> discoverers = new ArrayList<>();

	private static DefaultContentDiscoveryService instance = new DefaultContentDiscoveryService();

	private DefaultContentDiscoveryService() {
		addDefaultDiscoverers();
	}

	public static ContentTypeDiscoveryService instance() {
		return instance;
	}

	private void addDefaultDiscoverers() {

		discoverers.add(new QuakemlContentTypeDiscoverer());
		discoverers.add(new RespContentTypeDiscoverer());
		discoverers.add(new SacpzContentTypeDiscoverer());
		discoverers.add(new SeedContentTypeDiscoverer());
		discoverers.add(new StationXmlContentTypeDiscoverer());
	}

	@Override
	public ContentTypeDescriptor discover(Path path) throws UnkownContentTypeException, IOException {
		return discover(path, null);
	}

	@Override
	public ContentTypeDescriptor discover(Path path, Metadata metadata) throws UnkownContentTypeException, IOException {

		try (InputStream inputStream = Files.newInputStream(path)) {
			return discover(inputStream, metadata);
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream) throws UnkownContentTypeException, IOException {
		return discover(inputStream, null);
	}

	@Override
	public ContentTypeDescriptor discover(InputStream inputStream, Metadata metadata)
			throws UnkownContentTypeException, IOException {
		Objects.requireNonNull(inputStream, "inputStream cannot be null.");
		int numberOfBytesToRead = 400;
		try (InputStream unclosableInputStream = new UnclosableInputStream(
				inputStream instanceof BufferedInputStream ? inputStream : new BufferedInputStream(inputStream))) {
			unclosableInputStream.mark(numberOfBytesToRead);
			byte[] bytes = new byte[numberOfBytesToRead];
			int bytesRead = unclosableInputStream.read(bytes);
			unclosableInputStream.reset();

			try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);) {
				for (Discoverer d : this.discoverers) {
					try {
						ContentTypeDescriptor contentTypeDescriptor = d.discover(byteArrayInputStream, metadata);
						if (log.isDebugEnabled()) {
							log.debug("The following type discriptor has been discovered {}", contentTypeDescriptor);
						}
						return contentTypeDescriptor;
					} catch (UnkownContentTypeException e) {
						// continue
					}
					try {
						if (byteArrayInputStream.markSupported()) {
							// unclosableInputStream.reset();
							byteArrayInputStream.reset();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			throw new UnkownContentTypeException("Couldn't determine file type");
		}
	}

	public void add(Discoverer discoverer) {
		Objects.requireNonNull(discoverer, "discoverer cannot be null.");
		this.discoverers.add(discoverer);
	}

	static class UnclosableInputStream extends FilterInputStream {

		protected UnclosableInputStream(InputStream in) {
			super(in);
		}

		@Override
		public void close() throws IOException {
			try {
				if (markSupported()) {
					reset();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
