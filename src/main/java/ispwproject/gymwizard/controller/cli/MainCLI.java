package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.util.exception.AttivitaDuplicataException;
import ispwproject.gymwizard.util.exception.AttivitaPienaException;
import ispwproject.gymwizard.util.exception.DAOException;

public class MainCLI {
    public static void start() throws DAOException, AttivitaDuplicataException, AttivitaPienaException {
        CLIState statoCorrente = CLIState.LOGIN;

        while (statoCorrente != CLIState.USCITA) {
            switch (statoCorrente) {
                case LOGIN ->
                        statoCorrente = new LoginCLIController().start();

                case DASHBOARD_CLIENTE ->
                        statoCorrente = new DashboardClienteCLIController().start();
/*
                case ATTIVITA -> statoCorrente = new AttivitaCLIController().start();
                case SCHEDA_CLIENTE -> statoCorrente = new VisualizzaListaSchedeCLIController().start();
                case GESTIONE_ABBONAMENTI -> statoCorrente = new StatoAbbonamentoCLIController().start();
                case CHAT -> statoCorrente = new ListaChatCLIController().start();
                case CODICE_ACCESSO -> statoCorrente = new CodiceAccessoCLIController().start();
*/
                case DASHBOARD_TRAINER ->
                        statoCorrente = new DashboardTrainerCLIController().start();
/*
                case LISTA_CLIENTI -> statoCorrente = new ListaClientiCLIController().start();
                case CHAT -> statoCorrente = new ListaChatCLIController().start();

 */
                case DASHBOARD_ADMIN ->
                        statoCorrente = new DashboardAdminCLIController().start();
                /*
                case ATTIVITA -> statoCorrente = new AttivitaCLIController().start();
                case REPORT -> statoCorrente = new ReportCLIController().start();
                case COMUNICAZIONI -> statoCorrente = new ComunicazioniCLIController().start();
                */
                // Puoi continuare ad aggiungere altri stati qui
                default -> {
                    System.out.println("❌ Stato non gestito: " + statoCorrente);
                    statoCorrente = CLIState.USCITA;
                }
            }
        }

        System.out.println("👋 Arrivederci!");
    }
}
