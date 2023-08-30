package br.com.ovnny.videocurator.handler;

public class ErrorAdvise {
    String message;
    String status;
    String code;
    String root;

    public ErrorAdvise(String message, String status, String code, String root) {
        this.message = message;
        this.status = status;
        this.code = code;
        this.root = root;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoot() {
        return root;
    }
}