package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;

import java.util.List;
import java.util.Scanner;

public class AttivitaCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() throws DAOException {
        List<Attivita> attivitaList = AttivitaController.getAttivitaDisponibili();

        System.out.println("\n📌 ATTIVITÀ DISPONIBILI:\n");
        for (int i = 0; i < attivitaList.size(); i++) {
            Attivita a = attivitaList.get(i);
            System.out.println((i + 1) + ". " + a.getNome() + " | " + a.getData() + " | " + a.getOraInizio() + "-" + a.getOraFine() + " | posti disponibili: " + a.getPostiDisponibili());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Seleziona un'attività per info dettagliate (numero): ");
        String scelta = sc.nextLine();

        if ("0".equals(scelta)) {
            System.out.println("🔙 Ritorno alla dashboard cliente...");
        } else {
            System.out.println("Attività " + scelta + " selezionata:\n");
            Attivita a = attivitaList.get(Integer.parseInt(scelta) - 1);
            System.out.println(a.getNome() + " | " + a.getData() + " | " + a.getOraInizio() + "-" + a.getOraFine() + " | posti disponibili: " + a.getPostiDisponibili());
            System.out.print("👉 Premi invio per tornare: ");
            scanner.nextLine();
        }
    }
}
