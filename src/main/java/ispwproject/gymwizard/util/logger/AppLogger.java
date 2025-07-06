package ispwproject.gymwizard.util.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {

    private static final Logger LOGGER = Logger.getLogger("GymWizardLogger");

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter()); // Puoi personalizzarlo se vuoi
        LOGGER.addHandler(handler);
        LOGGER.setUseParentHandlers(false); // Evita il doppio log sulla console
        LOGGER.setLevel(Level.ALL);         // Logga tutto (puoi filtrare in base al livello)
    }

    private AppLogger() {
        // Costruttore privato per evitare istanze
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
