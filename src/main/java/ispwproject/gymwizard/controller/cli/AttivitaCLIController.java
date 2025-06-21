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

        if(SessionManager.getInstance().getAttributo("homePage").equals("Admin")){
            System.out.println("\nc. Crea nuova attività");
        }

        System.out.println("\n📌 ATTIVITÀ DISPONIBILI:\n");
        for (int i = 0; i < attivitaList.size(); i++) {
            System.out.println((i + 1) + ". " + attivitaList.get(i).getNome() + " | " + attivitaList.get(i).getData() + " | " + attivitaList.get(i).getOraInizio() + "-" + attivitaList.get(i).getOraFine() + " | posti disponibili: " + attivitaList.get(i).getPostiDisponibili());
        }

        System.out.println("\n0. 🔙 Torna alla Dashboard");
        System.out.print("👉 Seleziona (numero): ");
        String scelta = scanner.nextLine();

        if ("0".equals(scelta)) {
            System.out.println("🔙 Ritorno alla dashboard cliente...");
        } else if (SessionManager.getInstance().getAttributo("homePage").equals("Admin") && "c".equals(scelta)){
            new CreaAttivitaCLIController().start();
        } else {
            Attivita a = attivitaList.get(Integer.parseInt(scelta) - 1);
            new RiepilogoAttivitaCLIController().start(a);
        }
    }
}
