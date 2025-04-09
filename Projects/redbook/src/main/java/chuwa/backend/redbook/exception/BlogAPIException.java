package chuwa.backend.redbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName: BlogAPIException
 * Package: chuwa.backend.redbook.exception
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/9 11:19
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogAPIException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    public BlogAPIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
    public BlogAPIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
