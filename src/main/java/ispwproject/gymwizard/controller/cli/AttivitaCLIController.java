package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.model.Attivita;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.List;
import java.util.Scanner;

public class AttivitaCLIController {

    private final Scanner scanner = new Scanner(System.in);

    public void start() throws DAOException {
        List<Attivita> attivitaList = AttivitaController.getAttivitaDisponibili();

        int i = 0;
        if(SessionManager.getInstance().getAttributo("homePage").equals("Admin")){
            System.out.println("\n1. Crea nuova attività");
            i++;
        }
        System.out.println("\n📌 ATTIVITÀ DISPONIBILI:\n");
        for (; i < attivitaList.size(); i++) {
            Attivita a = attivitaList.get(i);
            System.out.println((i + 1) + ". " + a.getNome() + " | " + a.getData() + " | " + a.getOraInizio() + "-" + a.getOraFine() + " | posti disponibili: " + a.getPostiDisponibili());
        }

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Seleziona un'attività per info dettagliate (numero): ");
        String scelta = scanner.nextLine();

        if ("0".equals(scelta)) {
            System.out.println("🔙 Ritorno alla dashboard cliente...");
        } else if (SessionManager.getInstance().getAttributo("homePage").equals("Admin") && "1".equals(scelta)){
            new CreaAttivitaCLIController().start();
        } else {
            Attivita a = attivitaList.get(Integer.parseInt(scelta) - 1);
            new RiepilogoAttivitaCLIController().start(a);
        }
    }
}
