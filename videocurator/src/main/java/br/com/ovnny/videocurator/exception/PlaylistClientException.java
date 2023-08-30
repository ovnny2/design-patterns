package br.com.ovnny.videocurator.exception;

import org.springframework.http.HttpStatus;

public class PlaylistClientException extends RuntimeException {
    String message;
    HttpStatus status;

    public PlaylistClientException(String message, HttpStatus status) {
        this.message = message;
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