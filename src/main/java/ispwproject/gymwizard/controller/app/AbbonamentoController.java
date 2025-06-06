package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.AbbonamentoDAO;
import ispwproject.gymwizard.model.Abbonamento;
import ispwproject.gymwizard.util.singleton.SessionManager;

public class AbbonamentoController {

    public static Abbonamento getDatiAbbonamento() {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        int idUtente = utente.getId();

        return AbbonamentoDAO.trovaAbbonamentoAttivoPerUtente(idUtente);
    }
}
