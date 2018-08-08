package com.demo.lombok.withoutlombok.model;

import com.google.common.base.Preconditions;

/**
 * Created by resulav on 06.08.2018.
 */
public class User {
	private Long id;
	private String name;

	public User() {
	}

	public User(Long id) {
		this.id = id;
	}
	
	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	private User(Builder builder) {
		setId(builder.id);
		setName(builder.name);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
	}

	/**
	 * {@code User} builder static inner class.
	 */
	public static final class Builder {
		private Long id;
		private String name;

		private Builder() {
		}

		/**
		 * Sets the {@code id} and returns a reference to this Builder so that the
		 * methods can be chained together.
		 *
		 * @param id the {@code id} to set
		 * @return a reference to this Builder
		 */
		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the {@code name} and returns a reference to this Builder so that the
		 * methods can be chained together.
		 *
		 * @param name the {@code name} to set
		 * @return a reference to this Builder
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Returns a {@code User} built from the parameters previously set.
		 *
		 * @return a {@code User} built with parameters of this {@code User.Builder}
		 */
		public User build() {
			Preconditions.checkNotNull(this.id, "id can not be null");
			return new User(this);
		}
	}
}
