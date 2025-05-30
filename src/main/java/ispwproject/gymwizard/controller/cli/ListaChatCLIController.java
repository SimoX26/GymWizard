package ispwproject.gymwizard.controller.cli;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListaChatCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\n📨 LISTA CHAT ATTIVE\n");

        // Simulazione di contatti/chat disponibili
        List<String> chatDisponibili = Arrays.asList(
                "1. Coach Mario",
                "2. Coach Lucia"
        );

        for (String chat : chatDisponibili) {
            System.out.println(chat);
        }

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Seleziona una chat: ");
        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1", "2" -> {
                String nomeCoach = scelta.equals("1") ? "Mario" : "Lucia";
                new ChatCLIController(nomeCoach).start();
            }
            case "0" -> System.out.println("🔙 Ritorno alla dashboard cliente...");
            default -> {
                System.out.println("❌ Scelta non valida.");
                start();  // ripeti menu
            }
        }
    }
}
