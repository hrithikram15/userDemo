package com.rest.mongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="Note Already Exists Exception ..")
public class NoteAlreadyExistsException extends Exception {

}
