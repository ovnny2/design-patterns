package br.com.ovnny.videocurator.handler;

import br.com.ovnny.videocurator.exception.PlaylistClientException;
import br.com.ovnny.videocurator.exception.PlaylistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VideoControllerAdvice {

    @ExceptionHandler({ PlaylistClientException.class })
    public ResponseEntity<?> handleBadRequestException(PlaylistClientException exception) {
        var response = new PlaylistClientException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ PlaylistException.class })
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception) {

        var response = new ErrorAdvise(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "400",
                this.getClass().getSimpleName()
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(exception.getBody());
    }
}