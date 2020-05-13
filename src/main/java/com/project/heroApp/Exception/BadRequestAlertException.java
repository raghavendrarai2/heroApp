package com.project.heroApp.Exception;

public class BadRequestAlertException extends RuntimeException {

	private final Long id;

	public BadRequestAlertException(final String message, final Long id) {
		super(message + id);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
