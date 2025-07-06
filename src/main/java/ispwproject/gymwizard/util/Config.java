package ispwproject.gymwizard.util;

import ispwproject.gymwizard.util.logger.AppLogger;

import java.util.logging.Level;

public class Config {

    private Config() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static boolean demoMode = false; // valore predefinito
    private static boolean override = false;

    public static boolean isDemoMode() {
        return demoMode;
    }

    public static void setDemoMode(boolean value) {
        demoMode = value;
        override = true;
        AppLogger.getLogger().log(Level.INFO, "[CONFIG] Modalità DEMO impostata a: {0}", value);

    }
}
