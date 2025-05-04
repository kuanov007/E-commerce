package uz.azamat.ui;

public class NotFoundOurProducts extends Throwable {
    public NotFoundOurProducts() {
    }

    public NotFoundOurProducts(String message) {
        super(message);
    }

    public NotFoundOurProducts(String message, Throwable cause) {
        super(message, cause);
    }
}
