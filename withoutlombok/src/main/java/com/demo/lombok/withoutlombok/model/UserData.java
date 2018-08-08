/**
 * 
 */
package com.demo.lombok.withoutlombok.model;

import com.google.common.base.Preconditions;

/**
 * @author resulav
 *
 */
public class UserData {

	private Long id;
	private String name;

	public UserData(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		Preconditions.checkNotNull(id, "id can not be null");
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserData userData = (UserData) o;

		if (id != null ? !id.equals(userData.id) : userData.id != null)
			return false;
		return name != null ? name.equals(userData.name) : userData.name == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserData{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
