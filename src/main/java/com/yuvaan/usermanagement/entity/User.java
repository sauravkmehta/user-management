package com.yuvaan.usermanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User entity class which will be stored in tabular form in database.
 * @author saurav
 *
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 5897317142586511749L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String firstName;

	private String lastName;

	private String username;

	private LocalDate dateOfBirth;

	private String email;

	public User() {
		super();
	}

	public User(final String firstName, final String lastName, final String username, final String email,
			final LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + "]";
	}

}
