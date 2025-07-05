package ispwproject.gymwizard.controller.app;

import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.DAO.EsercizioSchedaDAO;
import ispwproject.gymwizard.util.DAO.SchedaDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;

import java.util.List;

public class SchedaController {

    public List<EsercizioScheda> getEserciziScheda(int idScheda){
        return EsercizioSchedaDAO.getEserciziByScheda(idScheda);
    }

    public List<Scheda> getSchedeByIdCliente(int idCliente){
        Utente loggato = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (loggato == null) {
            throw new SecurityException("Utente non loggato.");
        }

        // Se il chiamante è il cliente stesso, tutto ok
        // Altrimenti, accettiamo l’accesso perché siamo nella GUI del trainer (dove si seleziona il cliente manualmente)
        return SchedaDAO.getInstance().getSchedeByUtente(idCliente);
    }

    public void aggiungiEsercizio(String nomeEsercizio, int serie, int ripetizioni, String note) throws EsercizioDuplicatoException {
        Scheda scheda = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        int idScheda = scheda.getId();

        // Controllo duplicati
        if (EsercizioSchedaDAO.getInstance().existsEsercizio(idScheda, nomeEsercizio)) {
            throw new EsercizioDuplicatoException(nomeEsercizio);
        }

        EsercizioScheda nuovo = new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note);
        EsercizioSchedaDAO.getInstance().insertEsercizio(nuovo);
    }

    public void creaScheda(String nome) throws DAOException {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");

        Scheda nuovaScheda = new Scheda(utente.getId(), nome);
        SchedaDAO.getInstance().insertScheda(nuovaScheda);
    }

    public static class EsercizioDuplicatoException extends Exception {
        public EsercizioDuplicatoException(String nomeEsercizio) {
            super("L'esercizio \"" + nomeEsercizio + "\" è già presente nella scheda.");
        }
    }
}
