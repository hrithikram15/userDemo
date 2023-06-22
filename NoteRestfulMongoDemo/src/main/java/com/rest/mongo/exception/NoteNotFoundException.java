package com.rest.mongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND,reason="Note Not Found Exception ..")
public class NoteNotFoundException extends Exception {

}
