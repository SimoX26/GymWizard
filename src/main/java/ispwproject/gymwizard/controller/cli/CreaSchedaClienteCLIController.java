package ispwproject.gymwizard.controller.cli;

import ispwproject.gymwizard.controller.app.SchedaController;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.view.CreaSchedaClienteView;

public class CreaSchedaClienteCLIController {
    private final CreaSchedaClienteView view = new CreaSchedaClienteView();
    private final SchedaController controller = new SchedaController();

    public CLIState start() {
        Utente clienteSelezionato = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        String clienteNome = clienteSelezionato.getUsername();

        String nomeScheda = view.chiediNomeScheda(clienteNome);
        String tipo = view.chiediTipoScheda(clienteNome);
        if (nomeScheda == null || nomeScheda.isBlank()) {
            view.mostraMessaggio("❌ Nome scheda non valido.");
            view.attesaInvio();
            return CLIState.SELEZIONA_SCHEDA;
        }

        try {
            controller.creaScheda(nomeScheda, tipo);  // Salva la scheda nel DB
            view.mostraMessaggio("✅ Scheda \"" + nomeScheda + "\" creata per " + clienteNome + ".");
            view.attesaInvio();
            return CLIState.SELEZIONA_SCHEDA;

        } catch (DAOException e) {
            view.mostraMessaggio("❌ Errore durante la creazione della scheda: " + e.getMessage());
            view.attesaInvio();
            return CLIState.SELEZIONA_SCHEDA;
        }
    }
}
