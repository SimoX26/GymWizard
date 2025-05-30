package ispwproject.gymwizard.controller.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatCLIController {

    private final Scanner scanner = new Scanner(System.in);
    private final List<String> messaggi = new ArrayList<>();
    private final String coach;

    public ChatCLIController(String coach) {
        this.coach = coach;
    }

    public void start() {
        System.out.println("\n💬 CHAT CON IL COACH " + coach.toUpperCase() + "\n");

        messaggi.add("Coach " + coach + ": Ciao! Come va oggi?");
        messaggi.add("Tu: Tutto bene, grazie!");

        mostraMessaggi();

        while (true) {
            System.out.print("\nScrivi un messaggio (oppure digita '0' per tornare): ");
            String input = scanner.nextLine();

            if ("0".equals(input)) {
                System.out.println("🔙 Uscita dalla chat con Coach " + coach + "...");
                return;
            }

            messaggi.add("Tu: " + input);
            messaggi.add("Coach " + coach + ": Risponderò appena possibile!");

            mostraMessaggi();
        }
    }

    private void mostraMessaggi() {
        System.out.println("\n🧾 Storico messaggi:");
        for (String msg : messaggi) {
            System.out.println(msg);
        }
    }
}
