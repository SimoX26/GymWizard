package ispwproject.gymwizard.view;

import java.util.List;
import java.util.Scanner;

public class VisualizzaListaSchedeView {
    private final Scanner scanner = new Scanner(System.in);

    public int mostraSchedeDisponibili(List<String> schede) {
        System.out.println("=== SCEGLI UNA SCHEDA ===");
        for (int i = 0; i < schede.size(); i++) {
            System.out.println((i + 1) + ". " + schede.get(i));
        }
        System.out.print("Scelta: ");
        return Integer.parseInt(scanner.nextLine()) - 1;
    }

    public void mostraEsercizi(List<String> esercizi) {
        System.out.println("=== ESERCIZI DELLA SCHEDA ===");
        for (int i = 0; i < esercizi.size(); i++) {
            System.out.println((i + 1) + ". " + esercizi.get(i));
        }
    }

    public int mostraMenuEsercizi() {
        System.out.println("1. Aggiungi esercizio");
        System.out.println("0. Torna indietro");
        System.out.print("Scelta: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
