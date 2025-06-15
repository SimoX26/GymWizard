package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.model.Attivita;

import java.util.Scanner;

public class RiepilogoAttivitaCLIController {
    private final Scanner scanner = new Scanner(System.in);

    public void start(Attivita a){
        System.out.println("\nAttività selezionata:\n");
        System.out.println(a.getNome() + " | " + a.getData() + " | " + a.getOraInizio() + "-" + a.getOraFine() + " | posti disponibili: " + a.getPostiDisponibili());
        System.out.print("👉 Premi invio per tornare: ");
        scanner.nextLine();
    }
}
