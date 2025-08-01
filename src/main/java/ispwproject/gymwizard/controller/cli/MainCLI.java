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

                case SELEZIONA_SCHEDA -> statoCorrente = new VisualizzaListaSchedeCLIController().start();
                case VISUALIZZA_ESERCIZI_SCHEDA -> statoCorrente = new VisualizzaEserciziSchedaCLIController().start();

                case STATO_ABBONAMENTO -> statoCorrente = new StatoAbbonamentoCLIController().start();
                case RINNOVA_ABBONAMENTO -> statoCorrente = new RinnovaAbbonamentoCLIController().start();
                case RIEPILOGO_ABBONAMENTO -> statoCorrente = new RiepilogoAbbonamentoCLIController().start();

                case LISTINO_ATTIVITA -> statoCorrente = new ListinoAttivitaCLIController().start();
                case VISUALIZZA_ATTIVITA -> statoCorrente = new VisualizzaAttivitaCLIController().start();
                case CREA_ATTIVITA -> statoCorrente = new CreaAttivitaCLIController().start();

                case CODICE_ACCESSO -> statoCorrente = new CodiceAccessoCLIController().start();

                case DASHBOARD_TRAINER ->
                        statoCorrente = new DashboardTrainerCLIController().start();

                case LISTA_CLIENTI -> statoCorrente = new ListaClientiCLIController().start();
                case CREA_SCHEDA_CLIENTE -> statoCorrente = new CreaSchedaClienteCLIController().start();
                case AGGIUNGI_ESERCIZIO -> statoCorrente = new AggiungiEsercizioCLIController().start();

                case CHAT -> statoCorrente = new ListaChatCLIController().start();


                case DASHBOARD_ADMIN ->
                        statoCorrente = new DashboardAdminCLIController().start();

                default -> {
                    System.out.println("❌ Stato non gestito: " + statoCorrente);
                    statoCorrente = CLIState.USCITA;
                }
            }
        }

        System.out.println("👋 Arrivederci!");
    }
}
