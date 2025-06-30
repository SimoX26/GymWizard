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

    public static List<EsercizioScheda> getEserciziScheda(int idScheda) throws DAOException {
        return EsercizioSchedaDAO.getEserciziByClientId(idScheda);
    }

    public static List<Scheda> getNomiSchedeByIdCliente(){
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");
        return SchedaDAO.getInstance().getSchedeByUtente(utente.getId());
    }

    public void aggiungiEsercizio(String nomeEsercizio, int serie, int ripetizioni, String note)
            throws DAOException, EsercizioDuplicatoException {

        // Recupera l'id della scheda selezionata
        Scheda scheda = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        int idScheda =  scheda.getId();

        // Controllo duplicati
        if (EsercizioSchedaDAO.getInstance().existsEsercizio(idScheda, nomeEsercizio)) {
            throw new EsercizioDuplicatoException(nomeEsercizio);
        }

        EsercizioScheda nuovo = new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note);
        EsercizioSchedaDAO.getInstance().insertEsercizio(nuovo);
    }

    public void creaScheda(String nome) {
        Utente utente = (Utente) SessionManager.getInstance().getAttributo("utente");

        // Costruzione della scheda
        Scheda nuovaScheda = new Scheda(utente.getId(), nome);

        // Inserimento nel database
        boolean successo = SchedaDAO.getInstance().insertScheda(nuovaScheda);

        if (!successo) {
            throw new RuntimeException("Errore durante la creazione della scheda.");
        }
    }

    public static class EsercizioDuplicatoException extends Exception {
        public EsercizioDuplicatoException(String nomeEsercizio) {
            super("L'esercizio \"" + nomeEsercizio + "\" è già presente nella scheda.");
        }
    }
}
