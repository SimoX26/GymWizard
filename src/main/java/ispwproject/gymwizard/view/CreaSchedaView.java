package ispwproject.gymwizard.view;

import java.util.Scanner;

public class CreaSchedaView {
    private final Scanner scanner = new Scanner(System.in);

    public String inserisciNomeScheda() {
        System.out.print("Inserisci nome nuova scheda: ");
        return scanner.nextLine();
    }
}
