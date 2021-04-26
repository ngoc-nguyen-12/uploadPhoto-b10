package vn.techmaster.crm.exception;

import org.springframework.http.HttpStatus;

public class PhotoException extends RuntimeException {
  public HttpStatus status;
  public PhotoException(String message, Throwable cause, HttpStatus status) {
    super(message, cause, false, false);
    this.status = status;
  }

  public PhotoException(String message, HttpStatus status) {
    super(message, null, false, false);
    this.status = status;
  }

  public PhotoException(String message) {
    super(message, null, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }

  public PhotoException(String message, Throwable cause) {
    super(message, cause, false, false);
    this.status = HttpStatus.BAD_REQUEST;
  }
}
