package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.AttivitaController;
import ispwproject.gymwizard.controller.demo.DemoFactory;
import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.view.CreaAttivitaView;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreaAttivitaCLIController {

    private final CreaAttivitaView view = new CreaAttivitaView();
    private final AttivitaController controller = DemoFactory.getAttivitaController(); // ✅ Controller dinamico

    public CLIState start() {
        view.mostraTitolo();

        String nome = view.chiediStringa("🏷️ Nome attività: ");
        String descrizione = view.chiediStringa("🗒️ Descrizione attività: ");
        LocalDate data = LocalDate.parse(view.chiediStringa("📅 Data (YYYY-MM-DD): "));
        LocalTime oraInizio = LocalTime.parse(view.chiediStringa("⏰ Ora inizio (HH:MM): "));
        LocalTime oraFine = LocalTime.parse(view.chiediStringa("⏱️ Ora fine (HH:MM): "));
        int posti = view.chiediNumero("👥 Numero posti disponibili: ");
        String nomeTrainer = view.chiediStringa("👤 Nome del trainer: ");

        try {
            controller.creaAttivita(nome, descrizione, data, oraInizio, oraFine, posti, nomeTrainer); // ✅ uso dinamico
            view.mostraMessaggio("✅ Attività creata con successo!");
        } catch (DAOException e) {
            view.mostraMessaggio("❌ Errore durante la creazione: " + e.getMessage());
        } catch (AttivitaDuplicataException e) {
            view.mostraMessaggio("⚠️ Attività già esistente: " + e.getMessage());
        }

        view.attesaInvio();
        return CLIState.LISTINO_ATTIVITA;
    }
}
