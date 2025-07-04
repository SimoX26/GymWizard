package ispwproject.gymwizard.view;

import ispwproject.gymwizard.model.Attivita;

import java.util.List;
import java.util.Scanner;

public class ListinoAttivitaView {

    private final Scanner scanner = new Scanner(System.in);

    public int mostraAttivita(List<Attivita> attivitaList, boolean isAdmin) {
        while (true) {
            System.out.println("\n📌 ATTIVITÀ DISPONIBILI:\n");

            for (int i = 0; i < attivitaList.size(); i++) {
                Attivita a = attivitaList.get(i);
                System.out.printf("%d. %s | %s | %s-%s | posti disponibili: %d | nome trainer: %s\n",
                        i + 1, a.getNome(), a.getData(), a.getOraInizio(), a.getOraFine(), a.getPostiDisponibili(), a.getTrainerName());
            }

            if (isAdmin) {
                System.out.println("\nc. ➕ Crea nuova attività");
            }

            System.out.println("0. 🔙 Torna alla Dashboard");
            System.out.print("👉 Seleziona (numero o 'c'): ");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("0")) return 0;
            if (isAdmin && input.equalsIgnoreCase("c")) return -1;

            try {
                int scelta = Integer.parseInt(input);
                if (scelta >= 1 && scelta <= attivitaList.size()) return scelta;
            } catch (NumberFormatException ignored) {}

            System.out.println("❌ Scelta non valida.\n");
        }
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
