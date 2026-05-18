package top.inkly.view_service.domain.exceptions;

public class ViewNotFoundException extends RuntimeException {
    public ViewNotFoundException(String message) {
        super(message);
    }
}
