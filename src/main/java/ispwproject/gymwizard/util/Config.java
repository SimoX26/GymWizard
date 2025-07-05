package ispwproject.gymwizard.util;

public class Config {
    private static boolean demoMode = false; // valore predefinito
    private static boolean override = false;

    public static boolean isDemoMode() {
        return demoMode;
    }

    public static void setDemoMode(boolean value) {
        demoMode = value;
        override = true;
        System.out.println("[CONFIG] Modalità DEMO impostata a: " + value);
    }
}
