package com.fengtaiguang.ddd.framwork.web.exception;

public class ControllerException extends RuntimeException {

	public ControllerException() {
		super();
	}

	public ControllerException(String message) {
		super(message);
	}
}
