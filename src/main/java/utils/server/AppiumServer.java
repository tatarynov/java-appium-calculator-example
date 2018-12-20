package utils.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.jvm.hotspot.debugger.linux.LinuxOopHandle;
import utils.managers.PropertiesManager;

import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.Socket;
import java.time.LocalDate;
import java.util.NoSuchElementException;

/**
 * We can start the Appium server dynamically. With this class we can avoid the
 * manually starting of Appuim server. We need to install Nodejs app on the mac
 * and we need to set the below environment variables. we should the add below 2
 * variables in launchd.conf
 * NODE /usr/local/bin/node
 * APPIUM_PATH /Applications/Appium.app/Contents/Resources/node_modules/appium
 */
public class AppiumServer {

    public static final String HOST_NAME = "127.0.0.1";
    public static final String NODE_HOME = System.getenv("NODE_HOME");
    public static final String APPIUM_HOME = System.getenv("APPIUM_HOME");

    public static final int APPIUM_SERVER_START_TIMEOUT = 30;

    private int[] ports = new int[]{4721, 4722, 4723, 4724, 4725, 4726, 4727, 4728, 4729, 4730};
    private String appiumServerUrl;
    private Process process;

    private static final Logger LOG = LoggerFactory.getLogger(AppiumServer.class);


    public String startAppiumServer() throws IOException, InterruptedException {
        String command = "";

        final String homeDir = System.getProperty("user.dir") + File.separator + "target";
        final File appiumFilePath = new File(homeDir + File.separator + "logs");
        if (!appiumFilePath.exists()) appiumFilePath.mkdir();

        final File appiumLogsFile = new File(appiumFilePath, "appiumLogs_" + LocalDate.now() + ".log");
        appiumLogsFile.createNewFile();

        String appiumArgs = "--log-level debug --local-timezone --session-override --log " + appiumLogsFile.toString();
        int availablePort = 0;

        if (APPIUM_HOME == null) throw new NoSuchElementException("Environment variable not found for APPIUM_HOME");
        if (NODE_HOME == null) throw new NoSuchElementException("Environment variable not found for NODE_HOME");

        for (int i = 0; i < ports.length; i++) {
            String customPort = PropertiesManager.getInstance().getPort();
            if (!isPortInUse(HOST_NAME, customPort.isEmpty() ? ports[i] : Integer.parseInt(customPort))) {
                availablePort = customPort.isEmpty() ? ports[i] : Integer.parseInt(customPort);
                LOG.debug("Available port found: " + availablePort);
                break;
            }
        }
        if (availablePort == 0) {
            LOG.error("Port is not available");
            System.exit(1);
        } else {
            command = NODE_HOME + "  " + APPIUM_HOME + " ";
            command = command + " -a " + HOST_NAME + " -p " + availablePort + " ";
            command = command + " -cp " + (availablePort + 1000) + " -bp " + (availablePort + 2000);
            command = command + " --chromedriver-port " + (availablePort + 3000);
            command = command + " " + appiumArgs;
            LOG.debug("Command to start Appium Server: " + command);
        }

        process = Runtime.getRuntime().exec(command);

        LOG.info("Waiting for Appium server starts...");
        for (int i = 0; i < APPIUM_SERVER_START_TIMEOUT; i++) {
            if (isPortInUse(HOST_NAME, availablePort)) break;
            Thread.sleep(1000);
        }

        appiumServerUrl = "http://" + HOST_NAME + ":" + availablePort + "/wd/hub";
        return appiumServerUrl;
    }

    public boolean isPortInUse(String hostName, int portNumber) {
        try {
            Socket socket = new Socket(hostName, portNumber);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void killAppiumProcess() {
        if (process != null && process.isAlive()) process.destroy();
    }

    public String getAppiumServerUrl() {
        return appiumServerUrl;
    }

}