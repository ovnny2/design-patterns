package br.com.ovnny.videocurator.handler;

import br.com.ovnny.videocurator.exception.PlaylistClientException;
import br.com.ovnny.videocurator.exception.PlaylistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VideoControllerAdvice {

    @ExceptionHandler({ PlaylistClientException.class })
    public ResponseEntity<?> handleBadRequestException(PlaylistClientException exception) {
        var response = new PlaylistClientException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<?> handleIllegalArgumentException(PlaylistException exception) {

        var response = new ErrorAdvise(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                "400",
                this.getClass().getSimpleName()
        );

        return ResponseEntity.badRequest().body(response);
    }
}