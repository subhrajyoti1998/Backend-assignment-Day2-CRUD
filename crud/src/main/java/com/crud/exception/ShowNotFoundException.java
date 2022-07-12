package com.crud.exception;

public class ShowNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6099547199198572062L;

	public ShowNotFoundException(String msg) {
		super(msg);
	}
}