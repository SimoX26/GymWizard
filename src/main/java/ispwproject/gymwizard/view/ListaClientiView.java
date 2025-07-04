package ispwproject.gymwizard.view;

import java.util.List;
import java.util.Scanner;

public class ListaClientiView {
    private final Scanner scanner = new Scanner(System.in);

    public int selezionaCliente(List<String> clienti) {
        System.out.println("\n👥 Seleziona un cliente:");

        for (int i = 0; i < clienti.size(); i++) {
            System.out.println((i + 1) + ". " + clienti.get(i));
        }
        System.out.println("0. Torna alla dashboard");

        System.out.print("👉 Scelta: ");
        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            return scelta;
        } catch (NumberFormatException ignored) {}

        System.out.println("❌ Scelta non valida.");
        return selezionaCliente(clienti);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }
}
