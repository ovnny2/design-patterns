package br.com.ovnny.videocurator.exception;

import org.springframework.http.HttpStatus;

public class PlaylistException extends IllegalArgumentException {
    String message;
    HttpStatus status;

    public PlaylistException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}