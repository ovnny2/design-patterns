package br.com.ovnny.videocurator.exception;

import org.springframework.http.HttpStatusCode;

public class PlaylistClientException extends RuntimeException {
    String message;
    HttpStatusCode status;

    public PlaylistClientException(String message, HttpStatusCode status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatus() {
        return status;
    }
}