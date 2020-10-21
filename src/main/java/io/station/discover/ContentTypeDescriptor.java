package io.station.discover;

import java.nio.charset.Charset;

public interface ContentTypeDescriptor {

	public static final String RESP = "text.resp";
	public static final String SACPZ = "text.sacpz";
	public static final String STATIONXML = "station.xml";
	public static final String QUAKEML = "quake-ml.xml";


	public Charset getCharset();

	public long getContentLength();

	public String getMediaType();
	
	public String getResolvableType();
	
}
