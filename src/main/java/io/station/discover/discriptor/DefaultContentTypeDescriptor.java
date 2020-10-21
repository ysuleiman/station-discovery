package io.station.discover.discriptor;


import io.station.discover.ContentTypeDescriptor;

public class DefaultContentTypeDescriptor {

	private static ContentTypeDescriptor stationXmlTypeDescriptor = new StationXmlTypeDescriptor();
	private static ContentTypeDescriptor seedTypeDescriptor = new SeedTypeDescriptor();
	private static ContentTypeDescriptor sacpzTypeDescriptor = new SacpzTypeDescriptor();
	private static ContentTypeDescriptor respTypeDescriptor = new RespTypeDescriptor();
	
	public static ContentTypeDescriptor stationXmlTypeDescriptor() {
		return stationXmlTypeDescriptor;
	}

	public static ContentTypeDescriptor seedTypeDescriptor() {
		return seedTypeDescriptor;
	}

	public static ContentTypeDescriptor sacpzTypeDescriptor() {
		return sacpzTypeDescriptor;
	}

	public static ContentTypeDescriptor respTypeDescriptor() {
		return respTypeDescriptor;
	}

	
}
