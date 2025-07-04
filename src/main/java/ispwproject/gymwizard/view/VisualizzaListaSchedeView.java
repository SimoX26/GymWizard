package ispwproject.gymwizard.view;

import java.util.List;
import java.util.Scanner;

public class VisualizzaListaSchedeView {
    private final Scanner scanner = new Scanner(System.in);

    public int scegliScheda(List<String> schede, boolean mostraCreaScheda) {
        System.out.println("\n📋 SCEGLI UNA SCHEDA:");

        for (int i = 0; i < schede.size(); i++) {
            System.out.println((i + 1) + ". " + schede.get(i));
        }

        int indexOffset = schede.size() + 1;
        if (mostraCreaScheda) {
            System.out.println(indexOffset + ". ➕ Crea nuova scheda");
            indexOffset++;
        }

        System.out.println("0. 🔙 Torna indietro");
        System.out.print("👉 Scelta: ");

        try {
            int scelta = Integer.parseInt(scanner.nextLine());
            if (scelta == 0) return -1;
            if (scelta >= 1 && scelta <= schede.size()) return scelta - 1;
            if (mostraCreaScheda && scelta == schede.size() + 1) return -2;
        } catch (NumberFormatException ignored) {}

        System.out.println("❌ Scelta non valida.");
        return scegliScheda(schede, mostraCreaScheda);
    }

    public void mostraMessaggio(String msg) {
        System.out.println(msg);
    }

    public void attesaInvio() {
        System.out.print("👉 Premi invio per continuare...");
        scanner.nextLine();
    }
}
