package com.project.heroApp.Exception;

public class MovieNotFoundException extends RuntimeException {

	private final Long id;

	public MovieNotFoundException(final long id) {
		super("Movie could not be found with id: " + id);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	
}
