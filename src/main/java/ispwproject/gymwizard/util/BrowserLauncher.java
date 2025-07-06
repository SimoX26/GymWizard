package ispwproject.gymwizard.util;

import ispwproject.gymwizard.util.exception.BrowserAperturaException;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class BrowserLauncher {

    private BrowserLauncher() {
        // Utility class → costruttore privato
    }

    public static void apriUrlNelBrowser(String url) throws BrowserAperturaException {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                String os = System.getProperty("os.name").toLowerCase();

                List<String> comando;

                if (os.contains("linux")) {
                    comando = List.of("xdg-open", url);
                } else if (os.contains("mac")) {
                    comando = List.of("open", url);
                } else if (os.contains("win")) {
                    comando = List.of("rundll32", "url.dll,FileProtocolHandler", url);
                } else {
                    throw new BrowserAperturaException("Sistema operativo non supportato.");
                }


                new ProcessBuilder(comando).inheritIO().start();
            }
        } catch (IOException | URISyntaxException e) {
            throw new BrowserAperturaException("Impossibile aprire il browser per l'URL: " + url, e);
        }
    }
}
