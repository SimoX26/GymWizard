package ispwproject.gymwizard.util;

import ispwproject.gymwizard.util.logger.AppLogger;

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
        AppLogger.getLogger().info("[CONFIG] Modalità DEMO impostata a: " + value);
    }
}
