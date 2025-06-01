package ispwproject.gymwizard.controller.cli;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class SchedaAllenamentoCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n📄 SCHEDA DI ALLENAMENTO\n");

        String resourcePath = "images/Scheda2.png"; // ⬅️ percorso relativo nel classpath

        try {
            // Carica risorsa dal classpath
            URL imageUrl = getClass().getClassLoader().getResource(resourcePath);
            if (imageUrl == null) {
                System.out.println("❌ Scheda non trovata nel percorso: " + resourcePath);
                System.out.print("\n👉 Premi invio per tornare alla dashboard... ");
                scanner.nextLine();
                return;
            }

            try (InputStream in = imageUrl.openStream()) {
                // Copia in file temporaneo
                Path tempFile = Files.createTempFile("scheda_", ".png");
                Files.copy(in, tempFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                tempFile.toFile().deleteOnExit();

                // Apri nel visualizzatore predefinito
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(tempFile.toFile());
                    System.out.println("✅ Scheda aperta nel visualizzatore predefinito.");
                } else {
                    System.out.println("⚠️ Il sistema non supporta Desktop.getDesktop().open()");
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Errore durante l'apertura della scheda: " + e.getMessage());
        }

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Premi invio per tornare: ");
        scanner.nextLine();
    }
}

