package com.demo.lombok.withoutlombok.model;

/**
 * Created by resulav on 08.08.2018.
 */
public class UserValue {
	private final Long id;
	private String name;

	public UserValue(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserValue userValue = (UserValue) o;

		if (id != null ? !id.equals(userValue.id) : userValue.id != null)
			return false;
		return name != null ? name.equals(userValue.name) : userValue.name == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserValue [id=" + id + ", name=" + name + "]";
	}
	
}
