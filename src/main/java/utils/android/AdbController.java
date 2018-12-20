package utils.android;

import enums.Manufacturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.managers.PropertiesManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AdbController {

    private static final Logger LOG = LoggerFactory.getLogger(AdbController.class);

    private String adbLocation;
    private Process process;

    private static AdbController adbController;

    public static void main(String[] args) {
        AdbController instance = AdbController.getInstance();
        String[] devices = instance.getDevices();
        System.out.println(devices);
    }

    private AdbController() {
        this.adbLocation = new StringBuilder()
                .append(System.getenv("ANDROID_HOME"))
                .append("/platform-tools/adb ")
                .append(" -s ")
                .append(PropertiesManager.getInstance().getDeviceId())
                .append(" ")
                .toString();
    }

    public static synchronized AdbController getInstance() {
        if (adbController == null) {
            adbController = new AdbController();
        }
        return adbController;
    }

    /**
     * Returning array of connected devices ids
     */
    public String[] getDevices() {
        String[] split = executeAdbCommand(" devices").split("\n");
        return Arrays.stream(split)
                .map((i) -> i.split("\t")[0])
                .skip(1)
                .toArray(String[]::new);
    }


    /**
     * Uninstalls package by name
     *
     * @param packageName - package name to be uninstalled
     * @return true if packages was uninstalled successfully
     */
    public boolean uninstallPackage(String packageName) {
        String command = " uninstall " + packageName;
        String result = executeAdbCommand(command);
        LOG.debug("Uninstall package: " + packageName + " result is" + result);
        return result.contains("Success");
    }

    /**
     * Execute any ADB commands
     */
    public String executeAdbCommand(String command) {
        LOG.debug("Executing ADB Command google " + command);
        StringBuilder output = new StringBuilder();
        try {
            LOG.debug(adbLocation + command);
            if (System.getProperty("os.name").startsWith("Windows")) {
                process = Runtime.getRuntime().exec(adbLocation + command);
            } else {
                process = Runtime.getRuntime().exec(new String[]{"bash", "-c", adbLocation + command});
            }
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    /**
     * Matches the manufacturer name of the device with the user provided
     * manufacturer name
     */
    public boolean isManufacturer(final Manufacturer manufacturerName) {
        return executeAdbCommand(" shell getprop | grep ro.product.manufacturer")
                .toLowerCase()
                .contains(manufacturerName.getName());
    }
}

