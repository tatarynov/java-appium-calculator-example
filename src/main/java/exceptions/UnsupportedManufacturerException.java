package exceptions;

public class UnsupportedManufacturerException extends Exception {

    private String message;

    public UnsupportedManufacturerException(String s) {
        this.message = s;
    }
}
