package io.station.discover.discriptor;

import io.station.discover.ContentTypeDescriptor;

public abstract class AbstractTypeDescriptor implements ContentTypeDescriptor {
	private final String resolvableType;

	public AbstractTypeDescriptor(String resolvableType) {
		this.resolvableType = resolvableType;
	}

	public String getResolvableType() {
		return resolvableType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resolvableType == null) ? 0 : resolvableType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTypeDescriptor other = (AbstractTypeDescriptor) obj;
		if (resolvableType == null) {
			if (other.resolvableType != null)
				return false;
		} else if (!resolvableType.equals(other.resolvableType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractTypeDescriptor [resolvableType=" + resolvableType + "]";
	}


}
