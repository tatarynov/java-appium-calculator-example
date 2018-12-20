package utils.values;

public final class Waits {

    /**
     * Implicit timeout that is used for each call of driver.findElement(s)
     */
    public static final int DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;

    /**
     * Explicit timeout that is used for wait particular element with WebDriverWait/FluentWait
     */
    public static final int DEFAULT_EXPLICIT_WAIT_TIMEOUT = 5;

    /**
     * Polling timeout that is used for explicit wait to check if element appears/disappears
     */
    public static final int POLLING_TIMEOUT_MS = 250;

    /**
     * How long (in seconds) Appium will wait for a new command from the
     * client before assuming the client quit and ending the session.
     */
    public static final int NEW_COMMAND_TIMEOUT_SEC = 300000;

}
