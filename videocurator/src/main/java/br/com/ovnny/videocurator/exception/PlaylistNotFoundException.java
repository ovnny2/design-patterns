package br.com.ovnny.videocurator.exception;

import org.springframework.http.HttpStatus;

public class PlaylistNotFoundException extends RuntimeException {

    HttpStatus status;
    public PlaylistNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}