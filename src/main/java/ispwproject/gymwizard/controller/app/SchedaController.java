package ispwproject.gymwizard.controller.app;


import ispwproject.gymwizard.model.EsercizioScheda;
import ispwproject.gymwizard.model.Scheda;
import ispwproject.gymwizard.model.Utente;
import ispwproject.gymwizard.util.dao.EsercizioSchedaDAO;
import ispwproject.gymwizard.util.dao.SchedaDAO;
import ispwproject.gymwizard.util.filesystem.EsercizioSchedaFileDAO;
import ispwproject.gymwizard.util.filesystem.SchedaFileDAO;
import ispwproject.gymwizard.util.exception.DAOException;
import ispwproject.gymwizard.util.singleton.SessionManager;
import ispwproject.gymwizard.util.logger.AppLogger;
import java.util.logging.Level;

import java.util.List;

public class SchedaController {

    public  List<EsercizioScheda> getEserciziScheda(int idScheda) {
        return EsercizioSchedaDAO.getEserciziByScheda(idScheda);
    }

    public  List<Scheda> getSchedeByIdCliente(int idCliente) {
        Utente loggato = (Utente) SessionManager.getInstance().getAttributo("utente");

        if (loggato == null) {
            throw new SecurityException("Utente non loggato.");
        }

        // Accesso da parte del cliente stesso o del trainer
        return SchedaDAO.getInstance().getSchedeByUtente(idCliente);
    }

    public void creaScheda(String nome) throws DAOException {
        Utente cliente = (Utente) SessionManager.getInstance().getAttributo("clienteSelezionato");
        if (cliente == null) {
            throw new DAOException("Cliente non selezionato.");
        }

        Scheda nuovaScheda = new Scheda(cliente.getId(), nome);

        // Salva nel DB
        SchedaDAO.getInstance().insertScheda(nuovaScheda);

        // Salva nel FileSystem (data/clienti/<idCliente>/schede.json)
        try {
            SchedaFileDAO.getInstance().insertScheda(nuovaScheda);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.WARNING, "⚠️ Errore salvataggio su FileSystem", e);
        }
    }

    public void aggiungiEsercizio(String nomeEsercizio, int serie, int ripetizioni, String note) throws EsercizioDuplicatoException {
        Scheda schedaCorrente = (Scheda) SessionManager.getInstance().getAttributo("scheda");
        if (schedaCorrente == null) {
            throw new IllegalStateException("Scheda non selezionata.");
        }

        int idScheda = schedaCorrente.getId();

        // Verifica duplicati su DB
        try {
            if (EsercizioSchedaDAO.getInstance().existsEsercizio(idScheda, nomeEsercizio)) {
                throw new EsercizioDuplicatoException(nomeEsercizio);
            }
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante la verifica dell'esistenza dell'esercizio nel database");
            return; // Interrompe se il DB non è raggiungibile
        }

        EsercizioScheda nuovo = new EsercizioScheda(idScheda, nomeEsercizio, serie, ripetizioni, note);

        // Inserimento nel DB
        try {
            EsercizioSchedaDAO.getInstance().insertEsercizio(nuovo);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.SEVERE, "Errore durante l'inserimento dell'esercizio nel database");
            return; // Interrompe se il DB non è raggiungibile
        }

        // Inserimento nel FileSystem
        try {
            EsercizioSchedaFileDAO.getInstance().insertEsercizio(nuovo);
        } catch (Exception e) {
            AppLogger.getLogger().log(Level.WARNING, "Errore durante il salvataggio dell'esercizio nel FileSystem", e);
        }
    }


    public static class EsercizioDuplicatoException extends Exception {
        public EsercizioDuplicatoException(String nomeEsercizio) {
            super("L'esercizio \"" + nomeEsercizio + "\" è già presente nella scheda.");
        }
    }
}
