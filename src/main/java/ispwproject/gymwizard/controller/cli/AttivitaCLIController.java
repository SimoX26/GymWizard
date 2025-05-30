package ispwproject.gymwizard.controller.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AttivitaCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n📌 ATTIVITÀ DISPONIBILI:\n");

        // Simulazione lista attività (in futuro andrà letta da un controller applicativo)
        List<String> attivitaDisponibili = Arrays.asList(
                "1. Yoga - Lunedì 10:00",
                "2. Pilates - Martedì 16:00",
                "3. Cardio - Mercoledì 18:00",
                "4. CrossFit - Giovedì 12:00"
        );

        attivitaDisponibili.forEach(System.out::println);

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Seleziona un'attività per info dettagliate (non ancora attivo): ");
        String scelta = scanner.nextLine();

        if ("0".equals(scelta)) {
            System.out.println("🔙 Ritorno alla dashboard cliente...");
        } else {
            System.out.println("ℹ️ Funzionalità di dettaglio non ancora implementata.");
        }
    }
}
