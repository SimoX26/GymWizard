package ispwproject.gymwizard.view;

import java.util.Scanner;

public class MenuSchedaView {
    private final Scanner scanner = new Scanner(System.in);

    public int mostraMenuPrincipale() {
        System.out.println("=== SCHEDA ALLENAMENTO CLI ===");
        System.out.println("1. Seleziona una scheda");
        System.out.println("2. Crea nuova scheda");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
        return Integer.parseInt(scanner.nextLine());
    }
}