package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.Scanner;

public class RiepilogoAttivitaCLIController {
    private final Scanner scanner = new Scanner(System.in);

    public void start(Attivita a) throws DAOException, AttivitaPienaException {
        System.out.println("\nAttività selezionata:\n");
        System.out.println(a.getNome() + " | " + a.getData() + " | " + a.getOraInizio() + "-" + a.getOraFine() + " | posti disponibili: " + a.getPostiDisponibili() + " | nome trainer: " + a.getTrainerName());
        System.out.print("\n1. Prenota un posto per l'attività ");
        System.out.print("\n0. Tornare alla pagina precedente");
        System.out.print("👉 : ");
        String scelta = scanner.nextLine();

        switch (scelta) {
            case "1" -> AttivitaController.prenotaAttivita(a);
            case "0" -> System.out.println("Funzionalità non ancora implementata!");
            default -> System.out.println("❌ Scelta non valida.");
        }
    }
}
