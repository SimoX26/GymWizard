package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.util.exception.DAOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class CreaAttivitaCLIController {
    private final Scanner scanner = new Scanner(System.in);

    public void start() throws DAOException {
        System.out.println("\n=== CREA NUOVA ATTIVITÀ ===");

        System.out.print("Nome attività: ");
        String nome = scanner.nextLine();

        System.out.print("Descrizione: ");
        String descrizione = scanner.nextLine();

        System.out.print("Data (formato yyyy-mm-dd): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());

        System.out.print("Orario di inizio (formato HH:mm): ");
        LocalTime oraInizio = LocalTime.parse(scanner.nextLine());

        System.out.print("Orario di fine (formato HH:mm): ");
        LocalTime oraFine = LocalTime.parse(scanner.nextLine());

        System.out.print("Numero posti disponibili: ");
        int posti = scanner.nextInt();

        System.out.print("Nome del trainer: ");
        String trainerName = scanner.nextLine();

        scanner.nextLine(); // consuma newline

        new AttivitaController().creaAttivita(nome, descrizione, data, oraInizio, oraFine, posti, trainerName);

        new AttivitaCLIController().start();
    }
}
